package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityVault;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.utils.BlockColor;

/**
 * Meta layout follows the palette: bits 0-1 cardinal direction (south, west, north, east),
 * bits 2-3 vault_state, bit 4 ominous.
 */
public class BlockVault extends BlockSolidMeta {

    public static final int STATE_INACTIVE = 0;
    public static final int STATE_ACTIVE = 1;
    public static final int STATE_UNLOCKING = 2;
    public static final int STATE_EJECTING = 3;

    private static final BlockFace[] DIRECTIONS = {BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST};

    public BlockVault() {
        this(0);
    }

    public BlockVault(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return VAULT;
    }

    @Override
    public String getName() {
        return isOminous() ? "Ominous Vault" : "Vault";
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
    public BlockColor getColor() {
        return BlockColor.STONE_BLOCK_COLOR;
    }

    public int getState() {
        return (this.getDamage() >> 2) & 3;
    }

    public boolean isOminous() {
        return (this.getDamage() & 16) != 0;
    }

    public void setState(int state) {
        int meta = (this.getDamage() & ~12) | (state << 2);
        Block block = Block.get(VAULT, meta);
        this.level.setBlock(this, block, true, false);
    }

    public BlockFace getBlockFace() {
        return DIRECTIONS[this.getDamage() & 3];
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        BlockFace facing = player != null ? player.getHorizontalFacing().getOpposite() : BlockFace.NORTH;
        int dir = 0;
        for (int i = 0; i < DIRECTIONS.length; i++) {
            if (DIRECTIONS[i] == facing) {
                dir = i;
            }
        }
        this.setDamage((this.getDamage() & 0x1c) | dir);
        this.getLevel().setBlock(block, this, true);

        BlockEntity.createBlockEntity(BlockEntity.VAULT, this.getChunk(), BlockEntity.getDefaultCompound(this, BlockEntity.VAULT));
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        if (player == null || getState() != STATE_ACTIVE) {
            return false;
        }

        boolean ominous = isOminous();
        int keyId = ominous ? Item.OMINOUS_TRIAL_KEY : Item.TRIAL_KEY;
        if (item.getId() != keyId) {
            return false;
        }

        BlockEntity blockEntity = this.level.getBlockEntity(this);
        if (!(blockEntity instanceof BlockEntityVault)) {
            return false;
        }

        BlockEntityVault vault = (BlockEntityVault) blockEntity;
        if (vault.hasRewarded(player)) {
            this.level.addLevelSoundEvent(this, LevelSoundEventPacket.SOUND_VAULT_REJECT_REWARDED_PLAYER);
            return true;
        }

        if (!player.isCreative()) {
            player.getInventory().decreaseCount(player.getInventory().getHeldItemIndex());
        }
        this.level.addLevelSoundEvent(this, LevelSoundEventPacket.SOUND_VAULT_INSERT_ITEM);
        vault.unlock(player, ominous);
        return true;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(Block.VAULT));
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[0];
    }
}
