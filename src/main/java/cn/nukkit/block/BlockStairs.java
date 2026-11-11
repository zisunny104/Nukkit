package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.level.Level;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.SimpleAxisAlignedBB;
import cn.nukkit.utils.Faceable;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public abstract class BlockStairs extends BlockSolidMeta implements Faceable {

    private static final short[] FACES = {2, 1, 3, 0};

    protected BlockStairs(int meta) {
        super(meta);
    }

    @Override
    public double getMinY() {
        // TODO: this seems wrong
        return this.y + (this.getDamage() & 0x04) > 0 ? 0.5 : 0;
    }

    @Override
    public double getMaxY() {
        // TODO: this seems wrong
        return this.y + (this.getDamage() & 0x04) > 0 ? 1 : 0.5;
    }


    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        this.setDamage(FACES[player != null ? player.getDirection().getHorizontalIndex() : 0]);
        if ((fy > 0.5 && face != BlockFace.UP) || face == BlockFace.DOWN) {
            this.setDamage(this.getDamage() | 0x04); //Upside-down stairs
        }
        this.getLevel().setBlock(this, this, true, true);
        this.updateCorners();
        return true;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe()) {
            return new Item[]{
                  toItem()
            };
        } else {
            return new Item[0];
        }
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    /** Meta bits 0-1 direction of the high side, bit 2 upside down, bits 3-5 corner (0 none, 1 inner_left, 2 inner_right, 3 outer_left, 4 outer_right). */
    public static final int CORNER_SHIFT = 3;
    public static final int CORNER_MASK = 0x38;
    private static final BlockFace[] DIRECTIONS = {BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH, BlockFace.NORTH};

    public BlockFace getHighSide() {
        return DIRECTIONS[this.getDamage() & 0x03];
    }

    private static int directionIndex(BlockFace face) {
        for (int i = 0; i < DIRECTIONS.length; i++) {
            if (DIRECTIONS[i] == face) {
                return i;
            }
        }
        return 0;
    }

    private BlockStairs stairsAt(BlockFace side) {
        Block b = this.getSide(side);
        return b instanceof BlockStairs && ((b.getDamage() & 0x04) == (this.getDamage() & 0x04)) ? (BlockStairs) b : null;
    }

    /** Same rules as the official corner states (confirmed by the BDS capture of a pinwheel of four stairs). */
    public int computeCorner() {
        BlockFace facing = this.getHighSide();
        BlockStairs front = this.stairsAt(facing);
        if (front != null) {
            BlockFace f2 = front.getHighSide();
            if (f2.getAxis() != facing.getAxis() && !this.sameStairsAt(f2.getOpposite(), facing)) {
                return f2 == facing.rotateYCCW() ? 3 : 4;
            }
        }
        BlockStairs back = this.stairsAt(facing.getOpposite());
        if (back != null) {
            BlockFace f2 = back.getHighSide();
            if (f2.getAxis() != facing.getAxis() && !this.sameStairsAt(f2, facing)) {
                return f2 == facing.rotateYCCW() ? 1 : 2;
            }
        }
        return 0;
    }

    /** True when the block at side is a stairs of the same half and with the given high side. */
    private boolean sameStairsAt(BlockFace side, BlockFace highSide) {
        BlockStairs s = this.stairsAt(side);
        return s != null && s.getHighSide() == highSide;
    }

    /** Recalculates the corner of this block and of the four neighbours that are stairs. */
    public void updateCorners() {
        this.refreshCorner(this);
        for (BlockFace face : BlockFace.Plane.HORIZONTAL) {
            Block b = this.getSide(face);
            if (b instanceof BlockStairs) {
                this.refreshCorner((BlockStairs) b);
            }
        }
    }

    private void refreshCorner(BlockStairs s) {
        int meta = (s.getDamage() & ~CORNER_MASK) | (s.computeCorner() << CORNER_SHIFT);
        if (meta != s.getDamage()) {
            s.setDamage(meta);
            this.level.setBlock(s, s, true, true);
        }
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            int meta = (this.getDamage() & ~CORNER_MASK) | (this.computeCorner() << CORNER_SHIFT);
            if (meta != this.getDamage()) {
                this.setDamage(meta);
                this.level.setBlock(this, this, true, true);
            }
        }
        return 0;
    }

    @Override
    public boolean onBreak(Item item) {
        boolean r = super.onBreak(item);
        for (BlockFace face : BlockFace.Plane.HORIZONTAL) {
            Block b = this.getSide(face);
            if (b instanceof BlockStairs) {
                this.refreshCorner((BlockStairs) b);
            }
        }
        return r;
    }

    /** Horizontal rectangle {minX, minZ, maxX, maxZ} of the half of the block on the given side. */
    private static double[] half(BlockFace side) {
        switch (side) {
            case EAST: return new double[]{0.5, 0, 1, 1};
            case WEST: return new double[]{0, 0, 0.5, 1};
            case SOUTH: return new double[]{0, 0.5, 1, 1};
            default: return new double[]{0, 0, 1, 0.5};
        }
    }

    private static double[] quarter(BlockFace a, BlockFace b) {
        double[] x = half(a);
        double[] y = half(b);
        return new double[]{Math.max(x[0], y[0]), Math.max(x[1], y[1]), Math.min(x[2], y[2]), Math.min(x[3], y[3])};
    }

    private boolean hits(AxisAlignedBB bb, double[] r, double y0, double y1) {
        return bb.intersectsWith(new SimpleAxisAlignedBB(this.x + r[0], this.y + y0, this.z + r[1], this.x + r[2], this.y + y1, this.z + r[3]));
    }

    @Override
    public boolean collidesWithBB(AxisAlignedBB bb) {
        int damage = this.getDamage();
        boolean up = (damage & 0x04) > 0;
        double baseMin = up ? 0.5 : 0;
        double baseMax = up ? 1 : 0.5;
        double stepMin = up ? 0 : 0.5;
        double stepMax = up ? 0.5 : 1;
        if (hits(bb, new double[]{0, 0, 1, 1}, baseMin, baseMax)) {
            return true;
        }
        BlockFace facing = this.getHighSide();
        switch ((damage & CORNER_MASK) >> CORNER_SHIFT) {
            case 1:
                return hits(bb, half(facing), stepMin, stepMax) || hits(bb, quarter(facing.getOpposite(), facing.rotateYCCW()), stepMin, stepMax);
            case 2:
                return hits(bb, half(facing), stepMin, stepMax) || hits(bb, quarter(facing.getOpposite(), facing.rotateY()), stepMin, stepMax);
            case 3:
                return hits(bb, quarter(facing, facing.rotateYCCW()), stepMin, stepMax);
            case 4:
                return hits(bb, quarter(facing, facing.rotateY()), stepMin, stepMax);
            default:
                return hits(bb, half(facing), stepMin, stepMax);
        }
    }

    @Override
    public BlockFace getBlockFace() {
        return BlockFace.fromHorizontalIndex(this.getDamage() & 0x7);
    }

    @Override
    public WaterloggingType getWaterloggingType() {
        return WaterloggingType.WHEN_PLACED_IN_WATER;
    }
}
