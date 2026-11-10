package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.math.BlockFace;

public class BlockPaleOakLeaves extends BlockLeaves {

    public BlockPaleOakLeaves() {
        this(0);
    }

    public BlockPaleOakLeaves(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return PALE_OAK_LEAVES;
    }

    @Override
    public String getName() {
        return "Pale Oak Leaves";
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        this.setPersistent(true);
        return super.place(item, block, target, face, fx, fy, fz, player);
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    @Override
    protected boolean canDropApple() {
        return false;
    }

    @Override
    protected Item getSapling() {
        return new ItemBlock(Block.get(Block.PALE_OAK_SAPLING, 0));
    }

    @Override
    public boolean isPersistent() {
        return (this.getDamage() & 2) >>> 1 == 1;
    }

    @Override
    public void setPersistent(boolean persistent) {
        int value = (persistent ? 1 : 0) << 1;
        this.setDamage(this.getDamage() & ~2 | (value & 2));
    }

    @Override
    public boolean isCheckDecay() {
        return (this.getDamage() & 1) == 1;
    }

    @Override
    public void setCheckDecay(boolean updateBit) {
        this.setDamage(this.getDamage() & ~1 | ((updateBit ? 1 : 0) & 1));
    }
}
