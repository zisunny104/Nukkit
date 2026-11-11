package cn.nukkit.block;

import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;

public class BlockStairsCyanWool extends BlockStairs {

    public BlockStairsCyanWool() {
        this(0);
    }

    public BlockStairsCyanWool(int meta) {
        super(meta);
    }
    
    @Override
    public int getId() {
        return CYAN_WOOL_STAIRS;
    }
    
    @Override
    public String getName() {
        return "Cyan Wool Stairs";
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
