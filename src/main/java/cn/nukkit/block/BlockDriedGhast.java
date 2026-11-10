package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.Faceable;

/**
 * Data: bits 0-1 the facing (south, west, north, east), bits 2-3 the rehydration level.
 * TODO: rehydrating in water and turning into a happy ghast.
 */
public class BlockDriedGhast extends BlockTransparentMeta implements Faceable {

    public BlockDriedGhast() {
        this(0);
    }

    public BlockDriedGhast(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DRIED_GHAST;
    }

    @Override
    public String getName() {
        return "Dried Ghast";
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
        return 0;
    }

    @Override
    public double getResistance() {
        return 1;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public double getMinX() {
        return this.x + 0.1875;
    }

    @Override
    public double getMaxX() {
        return this.x + 0.8125;
    }

    @Override
    public double getMinZ() {
        return this.z + 0.1875;
    }

    @Override
    public double getMaxZ() {
        return this.z + 0.8125;
    }

    @Override
    public double getMaxY() {
        return this.y + 0.625;
    }
}
