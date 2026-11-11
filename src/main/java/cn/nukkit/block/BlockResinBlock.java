package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockResinBlock extends BlockSolid {

    public BlockResinBlock() {
    }

    @Override
    public String getName() {
        return "Resin Block";
    }

    @Override
    public int getId() {
        return RESIN_BLOCK;
    }

    @Override
    public double getHardness() {
        return 1;
    }

    @Override
    public double getResistance() {
        return 3;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.DIRT_BLOCK_COLOR;
    }
}
