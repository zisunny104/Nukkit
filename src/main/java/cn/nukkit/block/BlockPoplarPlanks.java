package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPoplarPlanks extends BlockPlanks {

    public BlockPoplarPlanks() {
        this(0);
    }

    public BlockPoplarPlanks(int meta) {
         super(0);
    }

    @Override
    public int getId() {
        return POPLAR_PLANKS;
    }

    @Override
    public String getName() {
        return "Poplar Planks";
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}