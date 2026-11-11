package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.Faceable;

/**
 * A copper golem statue. Data: the facing (south, west, north, east).
 * TODO: changing the pose by interacting, turning back into a golem.
 */
public abstract class BlockGolemStatue extends BlockTransparentMeta implements Faceable {

    protected BlockGolemStatue(int meta) {
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
        if (!this.getLevel().setBlock(block, this, true, true)) {
            return false;
        }

        BlockEntity.createBlockEntity(BlockEntity.COPPER_GOLEM_STATUE, this.getChunk(), new CompoundTag()
                .putString("id", BlockEntity.COPPER_GOLEM_STATUE)
                .putInt("x", (int) block.x)
                .putInt("y", (int) block.y)
                .putInt("z", (int) block.z));
        return true;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
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
        return this.y + 0.875;
    }
}
