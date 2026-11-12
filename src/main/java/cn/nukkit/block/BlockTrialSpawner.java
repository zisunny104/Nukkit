package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

/**
 * Meta layout follows the palette: bits 0-2 trial_spawner_state, bit 3 ominous.
 */
public class BlockTrialSpawner extends BlockSolidMeta {

    public static final int STATE_INACTIVE = 0;
    public static final int STATE_WAITING_FOR_PLAYERS = 1;
    public static final int STATE_ACTIVE = 2;
    public static final int STATE_WAITING_FOR_REWARD_EJECTION = 3;
    public static final int STATE_EJECTING_REWARD = 4;
    public static final int STATE_COOLDOWN = 5;

    public BlockTrialSpawner() {
        this(0);
    }

    public BlockTrialSpawner(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return TRIAL_SPAWNER;
    }

    @Override
    public String getName() {
        return "Trial Spawner";
    }

    @Override
    public double getHardness() {
        return 50;
    }

    @Override
    public double getResistance() {
        return 50;
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
    public boolean isTransparent() {
        return true;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.STONE_BLOCK_COLOR;
    }

    public int getState() {
        return this.getDamage() & 7;
    }

    public boolean isOminous() {
        return (this.getDamage() & 8) != 0;
    }

    public void setState(int state) {
        this.setStateAndOminous(state, isOminous());
    }

    public void setStateAndOminous(int state, boolean ominous) {
        Block block = Block.get(TRIAL_SPAWNER, state | (ominous ? 8 : 0));
        this.level.setBlock(this, block, true, false);
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        this.setDamage(STATE_WAITING_FOR_PLAYERS);
        this.getLevel().setBlock(block, this, true);

        BlockEntity.createBlockEntity(BlockEntity.TRIAL_SPAWNER, this.getChunk(), BlockEntity.getDefaultCompound(this, BlockEntity.TRIAL_SPAWNER));
        return true;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(Block.TRIAL_SPAWNER));
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[0];
    }
}
