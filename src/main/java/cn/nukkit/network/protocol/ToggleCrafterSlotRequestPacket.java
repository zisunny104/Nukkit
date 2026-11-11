package cn.nukkit.network.protocol;

import lombok.ToString;

@ToString
public class ToggleCrafterSlotRequestPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.__INTERNAL__TOGGLE_CRAFTER_SLOT_REQUEST_PACKET;

    public int x;
    public int y;
    public int z;
    public int slot;
    public boolean disabled;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.x = this.getLInt();
        this.y = this.getLInt();
        this.z = this.getLInt();
        this.slot = (byte) this.getByte();
        this.disabled = this.getBoolean();
    }

    @Override
    public void encode() {
        this.encodeUnsupported();
    }
}
