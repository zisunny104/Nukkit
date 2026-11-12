package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockCamera extends BlockSolid {

    @Override
    public String getName() {
        return "Camera";
    }

    @Override
    public int getId() {
        return CAMERA_BLOCK;
    }

    @Override
    public double getHardness() {
        return 0;
    }

    @Override
    public double getResistance() {
        return 0;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.STONE_BLOCK_COLOR;
    }
}
