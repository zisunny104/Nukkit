package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPaleOakStairs extends BlockStairsWood {

    public BlockPaleOakStairs() {
        this(0);
    }

    public BlockPaleOakStairs(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return PALE_OAK_STAIRS;
    }

    @Override
    public String getName() {
        return "Pale Oak Stairs";
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
