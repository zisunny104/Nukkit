package cn.nukkit.blockentity;

import cn.nukkit.Player;
import cn.nukkit.block.BlockID;
import cn.nukkit.inventory.CrafterInventory;
import cn.nukkit.inventory.InventoryHolder;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;

import java.util.ArrayList;

public class BlockEntityCrafter extends BlockEntitySpawnable implements InventoryHolder, BlockEntityContainer, BlockEntityNameable {

    protected CrafterInventory inventory;

    public BlockEntityCrafter(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    protected void initBlockEntity() {
        super.initBlockEntity();

        if (!this.namedTag.contains("disabled_slots")) {
            this.namedTag.putShort("disabled_slots", 0);
        }
    }

    private void initInventory() {
        this.inventory = new CrafterInventory(this);

        if (this.namedTag.contains("Items") && this.namedTag.get("Items") instanceof ListTag) {
            ListTag<CompoundTag> list = this.namedTag.getList("Items", CompoundTag.class);
            for (CompoundTag compound : list.getAll()) {
                Item item = NBTIO.getItemHelper(compound);
                int slot = compound.getByte("Slot");
                if (item.getId() != 0 && item.getCount() > 0 && slot >= 0 && slot < 9) {
                    this.inventory.slots.put(slot, item);
                }
            }
        }
    }

    @Override
    public boolean isBlockEntityValid() {
        return level.getBlockIdAt(chunk, (int) x, (int) y, (int) z) == BlockID.CRAFTER;
    }

    @Override
    public String getName() {
        return this.hasName() ? this.namedTag.getString("CustomName") : "Crafter";
    }

    @Override
    public boolean hasName() {
        return this.namedTag.contains("CustomName");
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            this.namedTag.remove("CustomName");
            return;
        }

        this.namedTag.putString("CustomName", name);
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public Item getItem(int index) {
        return this.getInventory().getItem(index);
    }

    @Override
    public void setItem(int index, Item item) {
        this.getInventory().setItem(index, item);
    }

    /**
     * Bit i is set when slot i has been disabled by a player.
     */
    public int getDisabledSlots() {
        return this.namedTag.getShort("disabled_slots") & 0x1ff;
    }

    public boolean isSlotDisabled(int slot) {
        return (this.getDisabledSlots() & (1 << slot)) != 0;
    }

    public void setSlotDisabled(int slot, boolean disabled) {
        if (slot < 0 || slot >= 9) {
            return;
        }

        int mask = this.getDisabledSlots();
        if (disabled) {
            mask |= 1 << slot;
        } else {
            mask &= ~(1 << slot);
        }

        this.namedTag.putShort("disabled_slots", mask);
        this.chunk.setChanged();
        this.spawnToAll();
    }

    @Override
    public void saveNBT() {
        super.saveNBT();

        if (this.inventory != null) {
            ListTag<CompoundTag> list = new ListTag<>("Items");
            for (int index = 0; index < this.getSize(); index++) {
                Item item = this.inventory.getItem(index);
                if (item != null && item.getId() != Item.AIR && item.getCount() > 0) {
                    list.add(NBTIO.putItemHelper(item, index));
                }
            }
            this.namedTag.putList(list);
        }
    }

    @Override
    public CrafterInventory getInventory() {
        if (this.inventory == null) {
            this.initInventory();
        }
        return this.inventory;
    }

    @Override
    public CompoundTag getSpawnCompound() {
        CompoundTag c = new CompoundTag()
                .putString("id", BlockEntity.CRAFTER)
                .putInt("x", (int) this.x)
                .putInt("y", (int) this.y)
                .putInt("z", (int) this.z)
                .putShort("disabled_slots", this.getDisabledSlots());

        if (this.hasName()) {
            c.put("CustomName", this.namedTag.get("CustomName"));
        }

        return c;
    }

    @Override
    public void close() {
        if (!this.closed && this.inventory != null) {
            for (Player player : new ArrayList<>(this.inventory.getViewers())) {
                player.removeWindow(this.inventory);
            }
        }

        super.close();
    }

    @Override
    public void onBreak() {
        for (Item content : this.getInventory().getContents().values()) {
            level.dropItem(this, content);
        }
        this.getInventory().clearAll();
    }
}
