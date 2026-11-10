package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPoplarFenceGate extends BlockFenceGate {

    public BlockPoplarFenceGate() {
        this(0);
    }

    public BlockPoplarFenceGate(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return POPLAR_FENCE_GATE;
    }

    @Override
    public String getName() {
        return "Poplar Fence Gate";
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
