package cn.nukkit.block;

import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;

public class BlockStairsSulfurPolished extends BlockStairs {

    public BlockStairsSulfurPolished() {
        this(0);
    }

    public BlockStairsSulfurPolished(int meta) {
        super(meta);
    }
    
    @Override
    public int getId() {
        return POLISHED_SULFUR_STAIRS;
    }
    
    @Override
    public String getName() {
        return "Polished Sulfur Stairs";
    }
    
    @Override
    public double getHardness() {
        return 3.5;
    }
    
    @Override
    public double getResistance() {
        return 6;
    }
    
    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
    
    @Override
    public int getToolTier() {
        return ItemTool.TIER_WOODEN;
    }
    
    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }
    
    @Override
    public BlockColor getColor() {
        return BlockColor.GRAY_TERRACOTA_BLOCK_COLOR;
    }
}
