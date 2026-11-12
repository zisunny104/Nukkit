package cn.nukkit.level;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockWall;
import cn.nukkit.block.custom.properties.BlockProperties;
import cn.nukkit.block.properties.VanillaProperties;
import cn.nukkit.block.properties.WallConnectionType;
import cn.nukkit.level.format.leveldb.LevelDBConstants;
import cn.nukkit.level.format.leveldb.NukkitLegacyMapper;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.network.protocol.ProtocolInfo;
import cn.nukkit.utils.Utils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.cloudburstmc.nbt.NbtMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.NoSuchElementException;

@Log4j2
public class GlobalBlockPalette {

    private static boolean initialized;

    @Getter
    private static final BlockPalette currentBlockPalette = new BlockPalette(ProtocolInfo.CURRENT_PROTOCOL);
    @Getter
    private static final BlockPalette leveldbBlockPalette = new BlockPalette(LevelDBConstants.PALETTE_VERSION);
    
    public static void init() {
        if (initialized) {
            throw new IllegalStateException("GlobalBlockPalette was already generated!");
        }
        initialized = true;
        log.debug("Loading block palette...");

        //noinspection unchecked
        loadBlockStates((ListTag<CompoundTag>) Utils.loadTagResource("runtime_block_states.dat"), getCurrentBlockPalette());
        //noinspection unchecked
        loadBlockStates((ListTag<CompoundTag>) Utils.loadTagResource("runtime_block_states_" + LevelDBConstants.PALETTE_VERSION + ".dat"), getLeveldbBlockPalette());
    }

    /**
     * The shipped palettes only contain the default state of each wall, the connection states are derived from it.
     * Vanilla sorts them by west, south, east, north and finally the post bit, the last one changing first.
     */
    private static void registerWallStates(BlockPalette blockPalette) {
        WallConnectionType[] connections = WallConnectionType.values();

        for (int id = 0; id < Block.MAX_BLOCK_ID; id++) {
            Block block = Block.getPrototype(id, 0);
            if (!(block instanceof BlockWall)) {
                continue;
            }

            BlockProperties properties = ((BlockWall) block).getBlockProperties();
            boolean typed = properties.contains(BlockWall.WALL_TYPE);
            int typeCount = typed ? BlockWall.WallType.values().length : 1;

            for (int type = 0; type < typeCount; type++) {
                int typeMeta = typed
                        ? properties.setValue(0, BlockWall.WALL_TYPE.getName(), BlockWall.WallType.values()[type])
                        : 0;
                int runtimeId = blockPalette.getRuntimeId(id, typeMeta);
                // A palette that has the wall as a single entry followed by other blocks (the LevelDB palette for the newer
                // walls) has no room for the derived states: they all use the default state and the blocks after it keep theirs.
                int next = blockPalette.getLegacyFullId(runtimeId + 1);
                boolean room = next == -1 || (next >> Block.DATA_BITS) == id;

                for (WallConnectionType west : connections) {
                    int westMeta = properties.setValue(typeMeta, VanillaProperties.WALL_CONNECTION_TYPE_WEST.getName(), west);
                    for (WallConnectionType south : connections) {
                        int southMeta = properties.setValue(westMeta, VanillaProperties.WALL_CONNECTION_TYPE_SOUTH.getName(), south);
                        for (WallConnectionType east : connections) {
                            int eastMeta = properties.setValue(southMeta, VanillaProperties.WALL_CONNECTION_TYPE_EAST.getName(), east);
                            for (WallConnectionType north : connections) {
                                int northMeta = properties.setValue(eastMeta, VanillaProperties.WALL_CONNECTION_TYPE_NORTH.getName(), north);
                                int postMeta = properties.setBooleanValue(northMeta, VanillaProperties.WALL_POST.getName(), true);
                                if (room) {
                                    blockPalette.registerState(id, northMeta, runtimeId++, true);
                                    blockPalette.registerState(id, postMeta, runtimeId++, true);
                                } else {
                                    blockPalette.registerState(id, northMeta, runtimeId, false);
                                    blockPalette.registerState(id, postMeta, runtimeId, false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static String stateKey(String name, Map<String, ?> states) {
        StringBuilder key = new StringBuilder(name);
        new TreeMap<String, Object>(states).forEach((k, v) -> key.append('|').append(k).append('=').append(v));
        return key.toString();
    }

    /**
     * Some entries of the shipped LevelDB table point at a runtime id that belongs to another block (the placeholder
     * block of the table, or a block that had the same legacy id in an older version), so the block was saved and
     * loaded as that other block. Such an entry is moved to the palette state of its own name and properties, or
     * dropped when the palette has none.
     */
    private static int resolveLeveldbRuntimeId(CompoundTag state, List<NbtMap> palette, Map<String, Integer> byKey) {
        int runtimeId = state.getInt("runtimeId");
        String name = state.getString("name");
        if (runtimeId >= 0 && runtimeId < palette.size() && name.equals(palette.get(runtimeId).getString("name"))) {
            return runtimeId;
        }
        Map<String, Object> states = state.getCompound("states").parseValue();
        Integer exact = byKey.get(stateKey(name, states));
        if (exact != null) {
            return exact;
        }
        // The entry may leave out properties that the palette has (the stripped bit of a wood block): take the first state that agrees on the rest.
        for (int i = 0; i < palette.size(); i++) {
            NbtMap candidate = palette.get(i);
            if (!name.equals(candidate.getString("name"))) {
                continue;
            }
            NbtMap candidateStates = candidate.getCompound("states");
            boolean same = true;
            for (Map.Entry<String, Object> entry : states.entrySet()) {
                if (!String.valueOf(entry.getValue()).equals(String.valueOf(candidateStates.get(entry.getKey())))) {
                    same = false;
                    break;
                }
            }
            if (same) {
                return i;
            }
        }
        return -1;
    }

    private static void loadBlockStates(ListTag<CompoundTag> blockStates, BlockPalette blockPalette) {
        List<CompoundTag> stateOverloads = new ObjectArrayList<>();
        List<NbtMap> palette = null;
        Map<String, Integer> byKey = null;
        if (blockPalette == getLeveldbBlockPalette()) {
            palette = NukkitLegacyMapper.loadBlockPalette();
            byKey = new HashMap<>();
            for (int i = 0; i < palette.size(); i++) {
                byKey.putIfAbsent(stateKey(palette.get(i).getString("name"), palette.get(i).getCompound("states")), i);
            }
        }
        for (CompoundTag state : blockStates.getAll()) {
            if (palette != null) {
                int resolved = resolveLeveldbRuntimeId(state, palette, byKey);
                if (resolved == -1) {
                    continue;
                }
                state.putInt("runtimeId", resolved);
            }
            if (!registerBlockState(blockPalette, state, false)) {
                stateOverloads.add(state);
            }
        }

        for (CompoundTag state : stateOverloads) {
            log.debug("[{}] Registering block palette overload: {}", blockPalette.getProtocol(), state.getString("name"));
            registerBlockState(blockPalette, state, true);
        }

        registerWallStates(blockPalette);

        blockPalette.lock(); // prevent adding new states
    }

    private static boolean registerBlockState(BlockPalette blockPalette, CompoundTag state, boolean force) {
        int id = state.getInt("id");
        int data = state.getShort("data");
        int runtimeId = state.getInt("runtimeId");
        boolean stateOverload = state.getBoolean("stateOverload");

        if (stateOverload && !force) {
            return false;
        }

        blockPalette.registerState(id, data, runtimeId);
        return true;
    }

    public static int getOrCreateRuntimeId(int id, int meta) {
        return getCurrentBlockPalette().getRuntimeId(id, meta);
    }

    public static int getOrCreateRuntimeId(int legacyId) throws NoSuchElementException {
        return getCurrentBlockPalette().getRuntimeId(legacyId);
    }

    public static int getLegacyFullId(int runtimeId) {
       return getCurrentBlockPalette().getLegacyFullId(runtimeId);
    }
}
