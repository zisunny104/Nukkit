package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;

public class BlockHeavyCore extends BlockTransparent {

    @Override
    public int getId() {
        return HEAVY_CORE;
    }

    @Override
    public String getName() {
        return "Heavy Core";
    }

    @Override
    public double getHardness() {
        return 10;
    }

    @Override
    public double getResistance() {
        return 1200;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public double getMinX() {
        return this.x + 0.25;
    }

    @Override
    public double getMaxX() {
        return this.x + 0.75;
    }

    @Override
    public double getMinY() {
        return this.y;
    }

    @Override
    public double getMaxY() {
        return this.y + 0.5;
    }

    @Override
    public double getMinZ() {
        return this.z + 0.25;
    }

    @Override
    public double getMaxZ() {
        return this.z + 0.75;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe()) {
            return new Item[]{this.toItem()};
        }
        return new Item[0];
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.IRON_BLOCK_COLOR;
    }
}
