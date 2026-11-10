package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPoplarStairs extends BlockStairsWood {

    public BlockPoplarStairs() {
        this(0);
    }

    public BlockPoplarStairs(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return POPLAR_STAIRS;
    }

    @Override
    public String getName() {
        return "Poplar Stairs";
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
