package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.Faceable;

/**
 * A bed of two blocks. Data: bit 0 occupied, bit 1 head piece, bits 2-3 the facing (south, west, north, east); the head lies in front of the foot.
 * TODO: sleeping, setting the spawn point and the explosion in the nether and the end.
 */
public class BlockStrawBed extends BlockTransparentMeta implements Faceable {

    private static final int HEAD_BIT = 0x02;
    private static final int DIRECTION_SHIFT = 2;

    public BlockStrawBed() {
        this(0);
    }

    public BlockStrawBed(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STRAW_BED;
    }

    @Override
    public String getName() {
        return "Straw Bed";
    }

    @Override
    public BlockFace getBlockFace() {
        return BlockFace.fromHorizontalIndex((this.getDamage() >> DIRECTION_SHIFT) & 0x3);
    }

    public boolean isHeadPiece() {
        return (this.getDamage() & HEAD_BIT) != 0;
    }

    private BlockFace otherHalfSide() {
        return this.isHeadPiece() ? this.getBlockFace().getOpposite() : this.getBlockFace();
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        BlockFace facing = player != null ? player.getDirection() : BlockFace.SOUTH;
        Block next = block.getSide(facing);
        if (!block.down().isSolid() || !next.canBeReplaced() || !next.down().isSolid()) {
            return false;
        }

        int direction = facing.getHorizontalIndex() << DIRECTION_SHIFT;
        this.getLevel().setBlock(block, Block.get(STRAW_BED, direction), true, true);
        this.getLevel().setBlock(next, Block.get(STRAW_BED, direction | HEAD_BIT), true, true);
        return true;
    }

    @Override
    public boolean onBreak(Item item) {
        Block other = this.getSide(this.otherHalfSide());
        if (other.getId() == STRAW_BED) {
            this.getLevel().setBlock(other, Block.get(AIR), true, true);
        }
        return super.onBreak(item);
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            Block other = this.getSide(this.otherHalfSide());
            if (other.getId() != STRAW_BED || ((BlockStrawBed) other).isHeadPiece() == this.isHeadPiece() || !this.down().isSolid()) {
                this.getLevel().useBreakOn(this);
                return type;
            }
        }
        return 0;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(STRAW_BED, 0), 0);
    }

    @Override
    public double getHardness() {
        return 0.2;
    }

    @Override
    public double getResistance() {
        return 0.2;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public double getMaxY() {
        return this.y + 0.25;
    }
}
