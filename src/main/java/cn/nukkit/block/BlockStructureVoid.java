package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.utils.BlockColor;

public class BlockStructureVoid extends BlockTransparent {

    @Override
    public String getName() {
        return "Structure Void";
    }

    @Override
    public int getId() {
        return 217;
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
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean canBeReplaced() {
        return false;
    }

    @Override
    public boolean canHarvestWithHand() {
        return true;
    }

    @Override
    protected AxisAlignedBB recalculateCollisionBoundingBox() {
        return null;
    }

    @Override
    public double getMinX() {
        return this.x + 0.3125;
    }

    @Override
    public double getMaxX() {
        return this.x + 0.6875;
    }

    @Override
    public double getMinY() {
        return this.y + 0.3125;
    }

    @Override
    public double getMaxY() {
        return this.y + 0.6875;
    }

    @Override
    public double getMinZ() {
        return this.z + 0.3125;
    }

    @Override
    public double getMaxZ() {
        return this.z + 0.6875;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[]{this.toItem()};
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.TRANSPARENT_BLOCK_COLOR;
    }
}
