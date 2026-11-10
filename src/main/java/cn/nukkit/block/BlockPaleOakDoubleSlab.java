package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPaleOakDoubleSlab extends BlockDoubleSlabWood {

    public BlockPaleOakDoubleSlab() {
        this(0);
    }

    public BlockPaleOakDoubleSlab(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pale Oak Double Slab";
    }

    @Override
    public int getId() {
        return PALE_OAK_DOUBLE_SLAB;
    }

    @Override
    public int getSingleSlabId() {
        return PALE_OAK_SLAB;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
