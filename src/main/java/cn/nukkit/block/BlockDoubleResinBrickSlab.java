package cn.nukkit.block;

import cn.nukkit.item.ItemTool;

public class BlockDoubleResinBrickSlab extends BlockDoubleSlabBase {

    public BlockDoubleResinBrickSlab() {
        this(0);
    }

    public BlockDoubleResinBrickSlab(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return RESIN_BRICK_DOUBLE_SLAB;
    }

    @Override
    public String getSlabName() {
        return "Double Resin Brick Slab";
    }
    @Override
    public int getSingleSlabId() {
        return RESIN_BRICK_SLAB;
    }

    @Override
    public int getItemDamage() {
        return 0;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }
}
