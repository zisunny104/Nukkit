package cn.nukkit.inventory;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemDurable;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Position;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.nbt.tag.CompoundTag;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class GrindstoneInventory extends FakeBlockUIComponent {

    private static final int EQUIPMENT = 0;
    private static final int INGREDIENT = 1;

    public static final int GRINDSTONE_EQUIPMENT_UI_SLOT = 16;
    public static final int GRINDSTONE_INGREDIENT_UI_SLOT = 17;

    public GrindstoneInventory(PlayerUIInventory playerUI, Position position) {
        super(playerUI, InventoryType.GRINDSTONE, 16, position);
    }

    public Item getResult() {
        Item eq = getEquipment().clone();
        Item iq = getIngredient();
        if (eq.isNull() || eq.getCount() != 1 || (!(eq instanceof ItemDurable) && eq.getId() != Item.ENCHANTED_BOOK)) {
            return Item.get(Item.AIR);
        }
        if (!iq.isNull() && (iq.getId() != eq.getId() || iq.getCount() != 1)) {
            return Item.get(Item.AIR);
        }
        Map<Integer, Enchantment> curses = new LinkedHashMap<>();
        for (Item input : new Item[]{eq, iq}) {
            for (Enchantment enchantment : input.getEnchantments()) {
                if (isCurse(enchantment)) {
                    Enchantment previous = curses.get(enchantment.getId());
                    if (previous == null || previous.getLevel() < enchantment.getLevel()) {
                        curses.put(enchantment.getId(), enchantment);
                    }
                }
            }
        }
        CompoundTag tag = eq.getNamedTag();
        if (tag == null) tag = new CompoundTag();
        CompoundTag cleaned = tag.clone().remove("ench").remove("RepairCost");
        eq.setNamedTag(cleaned);
        if (eq.getId() == Item.ENCHANTED_BOOK && curses.isEmpty()) {
            Item book = Item.get(Item.BOOK);
            book.setNamedTag(cleaned.clone());
            eq = book;
        }
        if (!curses.isEmpty()) {
            eq.addEnchantment(curses.values().toArray(new Enchantment[0]));
        }
        if (!iq.isNull() && eq instanceof ItemDurable) {
            // Output durability is the sum of the durabilities of the two input items
            // plus 5% of the maximum durability of the output item (rounded down)
            // capped so it does not exceed the maximum durability of the output item
            // https://minecraft.wiki/w/Grindstone#Repairing_and_disenchanting
            eq.setDamage(Math.max(eq.getMaxDurability() - ((eq.getMaxDurability() - eq.getDamage()) + (iq.getMaxDurability() - iq.getDamage()) + NukkitMath.floorDouble(eq.getMaxDurability() * 0.05)) + 1, 0));
        }
        return eq;
    }

    private static boolean isCurse(Enchantment enchantment) {
        return enchantment.getId() == Enchantment.ID_BINDING_CURSE || enchantment.getId() == Enchantment.ID_VANISHING_CURSE;
    }

    /** Experience comes only from enchantments that the grindstone removes. */
    public static int getExperience(Item equipment, Item ingredient) {
        long total = 0;
        for (Item input : new Item[]{equipment, ingredient}) {
            if (input == null || input.isNull()) continue;
            for (Enchantment enchantment : input.getEnchantments()) {
                if (!isCurse(enchantment)) total += Math.max(0, enchantment.getMinEnchantAbility(enchantment.getLevel()));
            }
        }
        if (total <= 0) return 0;
        int maximum = (int) Math.min(total, Integer.MAX_VALUE - 1L);
        return ThreadLocalRandom.current().nextInt((maximum + 1) / 2, maximum + 1);
    }

    public Item getEquipment() {
        return getItem(EQUIPMENT);
    }

    public void setEquipment(Item equipment) {
        setItem(EQUIPMENT, equipment);
    }

    public Item getIngredient() {
        return getItem(INGREDIENT);
    }

    public void setIngredient(Item ingredient) {
        setItem(INGREDIENT, ingredient);
    }

    @Override
    public void onOpen(Player who) {
        super.onOpen(who);
        who.craftingType = Player.GRINDSTONE_WINDOW_ID;
    }
}
