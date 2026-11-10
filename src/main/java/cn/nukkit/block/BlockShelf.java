package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.Faceable;

/**
 * A wooden shelf that hangs on the wall behind it.
 * Data: bits 0-1 the facing (south, west, north, east), bit 2 powered, bits 3-4 the powered shelf type.
 * TODO: the three item slots and the redstone behavior, the shelf block entity is not created yet.
 */
public abstract class BlockShelf extends BlockTransparentMeta implements Faceable {

    private static final double DEPTH = 0.3125;

    protected BlockShelf(int meta) {
        super(meta);
    }

    @Override
    public BlockFace getBlockFace() {
        return BlockFace.fromHorizontalIndex(this.getDamage() & 0x3);
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        int direction = player != null ? player.getDirection().getOpposite().getHorizontalIndex() : 0;
        this.setDamage(direction & 0x3);
        return this.getLevel().setBlock(block, this, true, true);
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 3;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public int getBurnChance() {
        return 20;
    }

    @Override
    public int getBurnAbility() {
        return 30;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public double getMinX() {
        return this.x + (this.getBlockFace() == BlockFace.WEST ? 1 - DEPTH : 0);
    }

    @Override
    public double getMaxX() {
        return this.x + (this.getBlockFace() == BlockFace.EAST ? DEPTH : 1);
    }

    @Override
    public double getMinZ() {
        return this.z + (this.getBlockFace() == BlockFace.NORTH ? 1 - DEPTH : 0);
    }

    @Override
    public double getMaxZ() {
        return this.z + (this.getBlockFace() == BlockFace.SOUTH ? DEPTH : 1);
    }
}
