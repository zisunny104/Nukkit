package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;

/**
 * A plant that is placed on top of a block it can stand on and falls off when the block below is gone.
 */
public abstract class BlockGroundPlant extends BlockFlowable {

    protected BlockGroundPlant(int meta) {
        super(meta);
    }

    protected boolean canStandOn(Block down) {
        int id = down.getId();
        return id == Block.GRASS || id == Block.DIRT || id == Block.FARMLAND || id == Block.PODZOL || id == MYCELIUM || id == MOSS_BLOCK || id == MUD || id == MUDDY_MANGROVE_ROOTS;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        if (!this.canStandOn(this.down())) {
            return false;
        }
        return this.getLevel().setBlock(block, this, true);
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL && this.down().getId() == Item.AIR) {
            this.getLevel().useBreakOn(this);
            return Level.BLOCK_UPDATE_NORMAL;
        }
        return 0;
    }
}
