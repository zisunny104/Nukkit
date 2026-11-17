package cn.nukkit.network;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.player.PlayerCreationEvent;
import cn.nukkit.event.server.QueryRegenerateEvent;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.network.session.NetherNetPlayerSession;
import cn.nukkit.network.session.NetworkPlayerSession;
import com.google.common.base.Strings;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.internal.PlatformDependent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.cloudburstmc.netty.channel.nethernet.NetherNetChannelFactory;
import org.cloudburstmc.netty.channel.nethernet.NetherNetConstants;
import org.cloudburstmc.netty.channel.nethernet.config.NetherChannelOption;
import org.cloudburstmc.netty.channel.nethernet.signaling.JoinRefusal;
import org.cloudburstmc.netty.channel.nethernet.signaling.NetherNetHTTPServerSignaling;
import org.cloudburstmc.netty.channel.nethernet.signaling.PongData;
import org.cloudburstmc.netty.util.nethernet.Identity;
import org.cloudburstmc.netty.util.nethernet.NetherNetLogging;
import org.cloudburstmc.netty.util.nethernet.OperatorIdentity;
import org.cloudburstmc.netty.util.nethernet.TokenTrust;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;
import tel.schich.libdatachannel.LibDataChannelArchDetect;
import tel.schich.libdatachannel.PeerConnectionConfiguration;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
@Log4j2
public class NetherNetInterface implements AdvancedSourceInterface {

    private EventLoopGroup eventLoopGroup;
    private Channel netherNetChannel;
    private NetherNetHTTPServerSignaling netherNetSignaling;

    @Getter
    @Setter
    private Network network;
    private final Server server;
    private final NetherNetAddressBlocks addressBlocks = new NetherNetAddressBlocks();

    private final Map<InetSocketAddress, NetherNetPlayerSession> sessions = new HashMap<>();
    private final Queue<NetherNetPlayerSession> sessionCreationQueue = PlatformDependent.newMpscQueue();

    public NetherNetInterface(Server server) {
        this.server = server;
        this.network = server.getNetwork();

        try {
            LibDataChannelArchDetect.initialize();

            NetherNetLogging.setNativeLogLevel("WARN");

            String advertisedAddressesString = server.getPropertyString("advertised-addresses", "");
            Set<String> advertisedAddresses = advertisedAddressesString.isBlank() ? null : new HashSet<>(Arrays.asList(advertisedAddressesString.split(",")));

            File identityFile = new File("nethernet-identity.p12");
            OperatorIdentity identity = identityFile.isFile()
                    ? fromKeystore(identityFile, server.getPropertyString("nethernet-identity-password", "nethernet"))
                    : OperatorIdentity.fromPemOrCreate(new File("identity.pem"), "Nukkit");

            NetherNetHTTPServerSignaling.Builder signallingBuilder = new NetherNetHTTPServerSignaling.Builder()
                    .setIceOnLocalPort(false)
                    .setTokenTrust(new TokenTrust() {
                        @Override
                        public JwtClaims claims(Identity identity) throws Exception {
                            return (server.xboxAuth ? TokenTrust.MINECRAFT_AUTH : TokenTrust.ANY).claims(identity);
                        }

                        @Override
                        public void prepare() {
                            if (server.xboxAuth) {
                                TokenTrust.MINECRAFT_AUTH.prepare();
                            }
                        }
                    })
                    .setPlayerFilter((host, player) -> this.addressBlocks.isBlocked(player.remoteAddress()) ? JoinRefusal.REJECTED : null)
                    .setMaxConnectionsPerAddress(server.getPropertyInt("connections-per-address", 8))
                    .setAdvertisedAddresses(advertisedAddresses)
                    .setIdentity(identity)
                    .setMotdProvider((host, address, client) -> {
                        QueryRegenerateEvent info = this.server.getQueryInformation();

                        return new PongData.Builder()
                                .setServerName(info.getServerName())
                                .setLevelName(info.getWorld())
                                .setProtocol(ProtocolInfo.CURRENT_PROTOCOL)
                                .setVersion(ProtocolInfo.MINECRAFT_VERSION_NETWORK)
                                .setPlayerCount(info.getPlayerCount())
                                .setMaxPlayerCount(info.getMaxPlayerCount())
                                .setOnlineAuth(server.xboxAuth)
                                .setSelfSignedAuth(!server.xboxAuth)
                                .build();
                    });

            Path httpsConfig = Path.of("keystore.p12");
            String httpsCert = server.getPropertyString("nethernet-https-cert", "");
            String httpsKey = server.getPropertyString("nethernet-https-key", "");
            boolean pem = !httpsCert.isBlank() && !httpsKey.isBlank();
            boolean https = pem || Files.isRegularFile(httpsConfig);
            if (pem) {
                signallingBuilder.setHttpsPem(new File(httpsCert), new File(httpsKey));
            } else if (https) {
                signallingBuilder.setHttpsKeystore(httpsConfig.toFile(), server.getPropertyString("nethernet-keystore-password", ""));
            }

            String trustedProxies = server.getPropertyString("nethernet-trusted-proxies", "");
            if (!trustedProxies.isBlank()) {
                signallingBuilder.setForwardedForProxies(Arrays.asList(trustedProxies.split(",")));
            }
            this.netherNetSignaling = signallingBuilder.build();

            this.eventLoopGroup = new NioEventLoopGroup();

            String address = Strings.isNullOrEmpty(server.getIp()) ? "0.0.0.0" : server.getIp();
            int webrtcPort = server.getPropertyInt("webrtc-port", server.getPort());
            if (webrtcPort < 0 || webrtcPort > 65535) {
                throw new IllegalArgumentException("webrtc-port must be between 0 and 65535");
            }
            int webrtcMtu = server.getPropertyInt("webrtc-mtu", PeerConnectionConfiguration.AUTO_MTU);

            PeerConnectionConfiguration webrtcConfig = PeerConnectionConfiguration.DEFAULT
                    .withMaxMessageSize(NetherNetConstants.MAX_ADVERTISED_MESSAGE_SIZE)
                    .withMtu(webrtcMtu);

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventLoopGroup)
                    .channelFactory(NetherNetChannelFactory.server(this.netherNetSignaling))
                    .option(NetherChannelOption.NETHER_PEER_CONNECTION_CONFIG, webrtcConfig)
                    .option(NetherChannelOption.NETHER_SERVER_ICE_ADDRESS, new InetSocketAddress(address, webrtcPort))
                    .childHandler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel channel) {
                            NetherNetPlayerSession nukkitSession = new NetherNetPlayerSession(NetherNetInterface.this, channel);
                            channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext context) {
                                    if (NetherNetInterface.this.addressBlocks.isBlocked((InetSocketAddress) channel.remoteAddress())) {
                                        context.close();
                                        return;
                                    }

                                    NetherNetInterface.this.sessionCreationQueue.offer(nukkitSession);
                                    context.fireChannelActive();
                                }
                            });
                            channel.pipeline().addLast("nukkit-handler", nukkitSession);
                        }
                    });

            ChannelFuture bind = bootstrap.bind(new InetSocketAddress(address, server.getPort()));
            this.netherNetChannel = bind.channel();
            bind.sync();

            server.getLogger().info("NetherNet signaling started on " + (https ? "https://" : "http://") + address + ":" + server.getPort() + " (WebRTC on port " + webrtcPort + "/udp)");
        } catch (Exception | LinkageError failure) {
            this.shutdown();
            if (failure instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new IllegalStateException("Failed to start NetherNet", failure);
        }
    }

    @Override
    public boolean process() {
        NetherNetPlayerSession session;
        while ((session = this.sessionCreationQueue.poll()) != null) {
            InetSocketAddress address = null;

            try {
                address = (InetSocketAddress) session.getChannel().remoteAddress();
                if (!session.getChannel().isOpen() || session.getDisconnectReason() != null || this.addressBlocks.isBlocked(address)) {
                    session.disconnect("Connection refused");
                    continue;
                }

                PlayerCreationEvent event = new PlayerCreationEvent(this, Player.class, Player.class, null, address);
                this.server.getPluginManager().callEvent(event);

                this.sessions.put(event.getSocketAddress(), session);

                Constructor<? extends Player> constructor = event.getPlayerClass().getConstructor(SourceInterface.class, Long.class, InetSocketAddress.class);
                Player player = constructor.newInstance(this, event.getClientId(), event.getSocketAddress());
                session.setPlayer(player);
                this.server.addPlayer(address, player);
            } catch (Exception e) {
                this.server.getLogger().error("Failed to create Player", e);
                session.disconnect("Internal Server Error");
                if (address != null) { // Cast issue
                    this.sessions.remove(address);
                }
            }
        }

        Iterator<NetherNetPlayerSession> iterator = this.sessions.values().iterator();
        while (iterator.hasNext()) {
            NetherNetPlayerSession nukkitSession = iterator.next();
            Player player = nukkitSession.getPlayer();
            if (nukkitSession.getDisconnectReason() != null) {
                player.close(player.getLeaveMessage(), nukkitSession.getDisconnectReason(), false);
                iterator.remove();
            } else {
                nukkitSession.serverTick();
            }
        }
        return true;
    }

    @Override
    public int getNetworkLatency(Player player) {
        NetworkPlayerSession session = player.getNetworkSession();
        return session == null ? -1 : (int) session.getPing();
    }

    @Override
    public NetworkPlayerSession getSession(InetSocketAddress address) {
        return this.sessions.get(address);
    }

    @Override
    public void close(Player player) {
        this.close(player, "unknown reason");
    }

    @Override
    public void close(Player player, String reason) {
        NetworkPlayerSession playerSession = this.getSession(player.getSocketAddress());
        if (playerSession != null) {
            playerSession.disconnect(reason);
        }
    }

    @Override
    public void shutdown() {
        try {
            if (this.netherNetChannel != null) {
                this.netherNetChannel.close().awaitUninterruptibly();
            } else if (this.netherNetSignaling != null) {
                this.netherNetSignaling.close();
            }
        } finally {
            if (this.eventLoopGroup != null) {
                this.eventLoopGroup.shutdownGracefully(0, 2, TimeUnit.SECONDS).awaitUninterruptibly();
            }
            this.sessionCreationQueue.clear();
            this.sessions.clear();
        }
    }

    @Override
    public void emergencyShutdown() {
        this.shutdown();
    }

    @Override
    public void blockAddress(InetSocketAddress address) {
        this.addressBlocks.block(address, TimeUnit.DAYS.toSeconds(100));
    }

    @Override
    public void blockAddress(InetSocketAddress address, int timeout) {
        this.addressBlocks.block(address, timeout);
    }

    @Override
    public void unblockAddress(InetSocketAddress address) {
        this.addressBlocks.unblock(address);
    }

    @Override
    public void sendRawPacket(InetSocketAddress socketAddress, ByteBuf payload) {
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public Integer putPacket(Player player, DataPacket packet) {
        return this.putPacket(player, packet, false);
    }

    @Override
    public Integer putPacket(Player player, DataPacket packet, boolean needACK) {
        return this.putPacket(player, packet, needACK, false);
    }

    @Override
    public Integer putPacket(Player player, DataPacket packet, boolean needACK, boolean immediate) {
        NetworkPlayerSession session = player.getNetworkSession();
        if (session == null) {
            session = this.sessions.get(player.getSocketAddress());
        }
        if (session != null) {
            session.sendPacket(packet);
        }
        return null;
    }
    // Preserve the configured PKCS12 operator identity across upgrades.
    private static OperatorIdentity fromKeystore(File keystore, String password) throws GeneralSecurityException, IOException, JoseException {
        char[] pwd = password.toCharArray();

        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(keystore)) {
            ks.load(fis, pwd);
        }

        // Find the first key in the keystore and extract the certificate
        String alias = findKeyAlias(ks);
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, pwd);
        Certificate cert = ks.getCertificate(alias);
        PublicKey publicKey = cert.getPublicKey();

        // Extract the expiry and common name from the cert if they exist
        Instant expiry = null;
        String domain = "";
        if (cert instanceof X509Certificate) {
            X509Certificate x509 = (X509Certificate) cert;
            expiry = x509.getNotAfter().toInstant();
            domain = extractCommonName(x509.getSubjectX500Principal());
        }

        return new OperatorIdentity(privateKey, publicKey, expiry, domain);
    }

    private static String findKeyAlias(KeyStore keyStore) throws KeyStoreException {
        for (String candidate : Collections.list(keyStore.aliases())) {
            if (keyStore.isKeyEntry(candidate)) {
                return candidate;
            }
        }
        throw new KeyStoreException("No private key entry found in identity keystore");
    }

    private static String extractCommonName(X500Principal principal) {
        try {
            LdapName name = new LdapName(principal.getName());
            for (Rdn rdn : name.getRdns()) {
                if (rdn.getType().equalsIgnoreCase("CN")) {
                    return rdn.getValue().toString();
                }
            }
        } catch (InvalidNameException ignored) { }
        return "";
    }
}
