package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPaleOakPlanks extends BlockPlanks {

    public BlockPaleOakPlanks() {
        this(0);
    }

    public BlockPaleOakPlanks(int meta) {
         super(0);
    }

    @Override
    public int getId() {
        return PALE_OAK_PLANKS;
    }

    @Override
    public String getName() {
        return "Pale Oak Planks";
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}