package cn.nukkit.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.nbt.NBTIO;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.MobArmorEquipmentPacket;

import java.util.function.IntPredicate;

/**
 * Saddle and body armor (horse armor, nautilus armor, harness) of a rideable animal.
 * Minimal version: equip by right click, drop on death, saved in NBT, sent to clients.
 * Armor values and riding are not implemented yet.
 */
public class MountGear {

    public static final IntPredicate HORSE_ARMOR = id -> id == ItemID.LEATHER_HORSE_ARMOR || id == ItemID.IRON_HORSE_ARMOR
            || id == ItemID.GOLD_HORSE_ARMOR || id == ItemID.DIAMOND_HORSE_ARMOR
            || id == ItemID.COPPER_HORSE_ARMOR || id == ItemID.NETHERITE_HORSE_ARMOR;

    public static final IntPredicate NAUTILUS_ARMOR = id -> id >= ItemID.COPPER_NAUTILUS_ARMOR && id <= ItemID.NETHERITE_NAUTILUS_ARMOR;

    public static final IntPredicate HARNESS = id -> id == ItemID.WHITE_HARNESS || id == ItemID.LIGHT_GRAY_HARNESS
            || id == ItemID.GRAY_HARNESS || id == ItemID.BLACK_HARNESS || id == ItemID.BROWN_HARNESS || id == ItemID.RED_HARNESS
            || id == ItemID.ORANGE_HARNESS || id == ItemID.YELLOW_HARNESS || id == ItemID.LIME_HARNESS || id == ItemID.GREEN_HARNESS
            || id == ItemID.CYAN_HARNESS || id == ItemID.LIGHT_BLUE_HARNESS || id == ItemID.BLUE_HARNESS || id == ItemID.PURPLE_HARNESS
            || id == ItemID.MAGENTA_HARNESS || id == ItemID.PINK_HARNESS;

    private final Entity entity;
    private final IntPredicate bodyItems;
    private final boolean allowSaddle;

    private Item body = Item.get(Item.AIR);
    private boolean saddled;

    public MountGear(Entity entity, IntPredicate bodyItems, boolean allowSaddle) {
        this.entity = entity;
        this.bodyItems = bodyItems;
        this.allowSaddle = allowSaddle;
    }

    public void load(CompoundTag nbt) {
        if (nbt.contains("BodyArmor")) {
            this.body = NBTIO.getItemHelper(nbt.getCompound("BodyArmor"));
        }
        this.saddled = nbt.getBoolean("Saddled");
        this.entity.setDataFlag(Entity.DATA_FLAGS, Entity.DATA_FLAG_SADDLED, this.saddled);
    }

    public void save(CompoundTag nbt) {
        nbt.putBoolean("Saddled", this.saddled);
        if (this.body.getId() != Item.AIR) {
            nbt.putCompound("BodyArmor", NBTIO.putItemHelper(this.body));
        } else {
            nbt.remove("BodyArmor");
        }
    }

    /**
     * @return true when the held item was used to equip the animal
     */
    public boolean interact(Player player, Item hand) {
        if (this.allowSaddle && hand.getId() == ItemID.SADDLE && !this.saddled) {
            this.saddled = true;
            this.entity.setDataFlag(Entity.DATA_FLAGS, Entity.DATA_FLAG_SADDLED, true);
            this.consume(player);
            return true;
        }
        if (this.bodyItems.test(hand.getId()) && this.body.getId() == Item.AIR) {
            this.body = hand.clone();
            this.body.setCount(1);
            this.consume(player);
            this.sendTo(null);
            return true;
        }
        return false;
    }

    private void consume(Player player) {
        if (!player.isCreative()) {
            player.getInventory().decreaseCount(player.getInventory().getHeldItemIndex());
        }
    }

    public void drops(java.util.List<Item> drops) {
        if (this.saddled) {
            drops.add(Item.get(ItemID.SADDLE));
        }
        if (this.body.getId() != Item.AIR) {
            drops.add(this.body.clone());
        }
    }

    /**
     * Send the body item to one player, or to every viewer when player is null.
     */
    public void sendTo(Player player) {
        if (this.body.getId() == Item.AIR) {
            return;
        }
        MobArmorEquipmentPacket pk = new MobArmorEquipmentPacket();
        pk.eid = this.entity.getId();
        pk.slots = new Item[]{Item.get(Item.AIR), Item.get(Item.AIR), Item.get(Item.AIR), Item.get(Item.AIR)};
        pk.body = this.body;
        if (player != null) {
            player.dataPacket(pk);
        } else {
            for (Player viewer : this.entity.getViewers().values()) {
                viewer.dataPacket(pk);
            }
        }
    }
}
