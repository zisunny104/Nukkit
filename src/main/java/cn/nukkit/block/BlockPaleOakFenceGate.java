package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPaleOakFenceGate extends BlockFenceGate {

    public BlockPaleOakFenceGate() {
        this(0);
    }

    public BlockPaleOakFenceGate(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return PALE_OAK_FENCE_GATE;
    }

    @Override
    public String getName() {
        return "Pale Oak Fence Gate";
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
