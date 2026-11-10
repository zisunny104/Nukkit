package cn.nukkit.block;

import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;

public class BlockStairsBrownWool extends BlockStairs {

    public BlockStairsBrownWool() {
        this(0);
    }

    public BlockStairsBrownWool(int meta) {
        super(meta);
    }
    
    @Override
    public int getId() {
        return BROWN_WOOL_STAIRS;
    }
    
    @Override
    public String getName() {
        return "Brown Wool Stairs";
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
