package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntityCrafter;
import cn.nukkit.inventory.ContainerInventory;
import cn.nukkit.inventory.CraftingRecipe;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.inventory.InventoryHolder;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.utils.BlockColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Meta layout follows the palette: 0-11 orientation (front_top, alphabetical), 16 crafting, 32 triggered_bit.
 */
public class BlockCrafter extends BlockSolidMeta {

    private static final BlockFace[] FRONT = {
            BlockFace.DOWN, BlockFace.DOWN, BlockFace.DOWN, BlockFace.DOWN,
            BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH,
            BlockFace.UP, BlockFace.UP, BlockFace.UP, BlockFace.UP,
            BlockFace.WEST
    };
    private static final BlockFace[] TOP = {
            BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST,
            BlockFace.UP, BlockFace.UP, BlockFace.UP,
            BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST,
            BlockFace.UP
    };

    private static final int CRAFTING_BIT = 16;
    private static final int TRIGGERED_BIT = 32;

    public BlockCrafter() {
        this(0);
    }

    public BlockCrafter(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CRAFTER;
    }

    @Override
    public String getName() {
        return "Crafter";
    }

    @Override
    public double getHardness() {
        return 1.5;
    }

    @Override
    public double getResistance() {
        return 3.5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.STONE_BLOCK_COLOR;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    private int orientation() {
        int o = this.getDamage() & 0xf;
        return o < FRONT.length ? o : 0;
    }

    public BlockFace getFront() {
        return FRONT[orientation()];
    }

    public BlockFace getTop() {
        return TOP[orientation()];
    }

    public boolean isTriggered() {
        return (this.getDamage() & TRIGGERED_BIT) != 0;
    }

    public boolean isCrafting() {
        return (this.getDamage() & CRAFTING_BIT) != 0;
    }

    private void setFlags(boolean triggered, boolean crafting) {
        int meta = this.orientation();
        if (triggered) {
            meta |= TRIGGERED_BIT;
        }
        if (crafting) {
            meta |= CRAFTING_BIT;
        }
        this.setDamage(meta);
        this.level.setBlock((int) this.x, (int) this.y, (int) this.z, BlockLayer.NORMAL, this, false, false, false);
    }

    private static int orientationOf(BlockFace front, BlockFace top) {
        for (int i = 0; i < FRONT.length; i++) {
            if (FRONT[i] == front && TOP[i] == top) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        BlockFace front = BlockFace.NORTH;
        BlockFace top = BlockFace.UP;

        if (player != null) {
            BlockFace horizontal = player.getHorizontalFacing();
            double eyeY = player.y + player.getEyeHeight();
            boolean near = Math.abs(player.x - this.x) < 2 && Math.abs(player.z - this.z) < 2;

            if (near && eyeY - this.y > 2) {
                front = BlockFace.UP;
                top = horizontal;
            } else if (near && this.y - eyeY > 0) {
                front = BlockFace.DOWN;
                top = horizontal.getOpposite();
            } else {
                front = horizontal.getOpposite();
            }
        }

        this.setDamage(orientationOf(front, top));
        this.getLevel().setBlock(block, this, true);

        BlockEntity.createBlockEntity(BlockEntity.CRAFTER, this.getChunk(), BlockEntity.getDefaultCompound(this, BlockEntity.CRAFTER));
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        if (player == null) {
            return false;
        }

        BlockEntity blockEntity = this.level.getBlockEntity(this);
        if (!(blockEntity instanceof BlockEntityCrafter)) {
            return false;
        }

        player.addWindow(((BlockEntityCrafter) blockEntity).getInventory());
        return true;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(Block.CRAFTER));
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride() {
        BlockEntity blockEntity = this.level.getBlockEntity(this);
        if (!(blockEntity instanceof BlockEntityCrafter)) {
            return 0;
        }

        BlockEntityCrafter crafter = (BlockEntityCrafter) blockEntity;
        int count = 0;
        for (int slot = 0; slot < 9; slot++) {
            if (crafter.isSlotDisabled(slot) || !crafter.getInventory().getItem(slot).isNull()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_REDSTONE) {
            boolean powered = level.isBlockPowered(this) || level.isBlockPowered(this.getSideVec(BlockFace.UP));
            if (powered && !isTriggered()) {
                this.setFlags(true, isCrafting());
                level.scheduleUpdate(this, this, 4);
            } else if (!powered && isTriggered()) {
                this.setFlags(false, isCrafting());
            }
            return type;
        } else if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            if (isCrafting()) {
                this.setFlags(isTriggered(), false);
            } else {
                this.craft();
            }
            return type;
        }

        return 0;
    }

    public void craft() {
        BlockEntity blockEntity = this.level.getBlockEntity(this);
        if (!(blockEntity instanceof BlockEntityCrafter)) {
            return;
        }

        BlockEntityCrafter crafter = (BlockEntityCrafter) blockEntity;
        Inventory inv = crafter.getInventory();

        List<Item> inputs = new ArrayList<>();
        List<Integer> used = new ArrayList<>();
        for (int slot = 0; slot < 9; slot++) {
            Item item = inv.getItem(slot);
            if (crafter.isSlotDisabled(slot) || item.isNull()) {
                continue;
            }
            Item one = item.clone();
            one.setCount(1);
            inputs.add(one);
            used.add(slot);
        }

        CraftingRecipe recipe = inputs.isEmpty() ? null : this.level.getServer().getCraftingManager().matchRecipeByInputs(inputs);
        if (recipe == null) {
            this.level.addLevelSoundEvent(this, LevelSoundEventPacket.SOUND_CRAFTER_FAILED);
            return;
        }

        for (int slot : used) {
            Item item = inv.getItem(slot).clone();
            item.setCount(item.getCount() - 1);
            inv.setItem(slot, item.getCount() > 0 ? item : Item.get(Item.AIR));
        }

        this.output(recipe.getResult().clone());
        for (Item extra : recipe.getExtraResults()) {
            if (!extra.isNull()) {
                this.output(extra.clone());
            }
        }

        this.level.addLevelSoundEvent(this, LevelSoundEventPacket.SOUND_CRAFTER_CRAFT);
        this.setFlags(isTriggered(), true);
        this.level.scheduleUpdate(this, this, 6);
    }

    private void output(Item item) {
        BlockFace front = this.getFront();
        BlockEntity target = this.level.getBlockEntity(this.getSideVec(front));

        if (target instanceof InventoryHolder) {
            Inventory inventory = ((InventoryHolder) target).getInventory();
            if (inventory instanceof ContainerInventory) {
                Item[] left = inventory.addItem(item);
                item = left.length > 0 ? left[0] : null;
            }
        }

        if (item == null || item.isNull()) {
            return;
        }

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        Vector3 pos = this.add(
                0.5 + 0.7 * front.getXOffset(),
                0.5 + 0.7 * front.getYOffset() - (front.getAxis() == BlockFace.Axis.Y ? 0.125 : 0.15625),
                0.5 + 0.7 * front.getZOffset());
        double offset = rand.nextDouble() * 0.1 + 0.2;
        Vector3 motion = new Vector3(front.getXOffset() * offset, 0.1, front.getZOffset() * offset);
        this.level.dropItem(pos, item, motion);
    }

    @Override
    public boolean canBePushed() {
        return false; // block entity contents would be lost
    }
}
