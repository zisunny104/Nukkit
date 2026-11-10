package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPoplarDoubleSlab extends BlockDoubleSlabWood {

    public BlockPoplarDoubleSlab() {
        this(0);
    }

    public BlockPoplarDoubleSlab(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Double Slab";
    }

    @Override
    public int getId() {
        return POPLAR_DOUBLE_SLAB;
    }

    @Override
    public int getSingleSlabId() {
        return POPLAR_SLAB;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
