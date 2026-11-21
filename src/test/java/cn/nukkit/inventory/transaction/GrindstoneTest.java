package cn.nukkit.inventory.transaction;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.inventory.FakeBlockMenu;
import cn.nukkit.inventory.GrindstoneInventory;
import cn.nukkit.inventory.transaction.action.InventoryAction;
import cn.nukkit.item.Item;
import cn.nukkit.item.RuntimeItems;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GrindstoneTest {
    @BeforeAll static void initialize() {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        Enchantment.init();
    }

    private static Item enchanted(int item, int... enchantments) {
        Item result = Item.get(item);
        for (int id : enchantments) result.addEnchantment(Enchantment.getEnchantment(id));
        return result;
    }

    private static GrindstoneInventory inventory(Item equipment, Item ingredient) {
        GrindstoneInventory inventory = mock(GrindstoneInventory.class);
        when(inventory.getEquipment()).thenReturn(equipment);
        when(inventory.getIngredient()).thenReturn(ingredient);
        doCallRealMethod().when(inventory).getResult();
        return inventory;
    }

    @Test void removesRegularEnchantmentsButKeepsCursesNameAndCustomNbt() {
        Item original = enchanted(Item.DIAMOND_PICKAXE, Enchantment.ID_DURABILITY, Enchantment.ID_VANISHING_CURSE);
        original.setCustomName("\u5de5\u5177");
        original.setRepairCost(15);
        original.setNamedTag(original.getNamedTag().putString("PluginData", "kept"));
        Item output = inventory(original, Item.get(Item.AIR)).getResult();
        assertFalse(output.hasEnchantment(Enchantment.ID_DURABILITY));
        assertTrue(output.hasEnchantment(Enchantment.ID_VANISHING_CURSE));
        assertEquals("\u5de5\u5177", output.getCustomName());
        assertEquals("kept", output.getNamedTag().getString("PluginData"));
        assertEquals(0, output.getRepairCost());
        assertTrue(original.hasEnchantment(Enchantment.ID_DURABILITY));
        assertEquals(15, original.getRepairCost());
    }

    @Test void cursedBookStaysEnchantedAndNormalBookKeepsItsName() {
        Item cursed = enchanted(Item.ENCHANTED_BOOK, Enchantment.ID_DURABILITY, Enchantment.ID_BINDING_CURSE);
        Item output = inventory(cursed, Item.get(Item.AIR)).getResult();
        assertEquals(Item.ENCHANTED_BOOK, output.getId());
        assertTrue(output.hasEnchantment(Enchantment.ID_BINDING_CURSE));
        assertFalse(output.hasEnchantment(Enchantment.ID_DURABILITY));
        Item book = enchanted(Item.ENCHANTED_BOOK, Enchantment.ID_DURABILITY);
        book.setCustomName("\u66f8");
        output = inventory(book, Item.get(Item.AIR)).getResult();
        assertEquals(Item.BOOK, output.getId());
        assertEquals("\u66f8", output.getCustomName());
        assertFalse(output.hasEnchantments());
    }

    @Test void combiningTransfersCursesFromBothInputsAndRejectsDifferentTypes() {
        Item left = enchanted(Item.DIAMOND_PICKAXE, Enchantment.ID_BINDING_CURSE);
        Item right = enchanted(Item.DIAMOND_PICKAXE, Enchantment.ID_VANISHING_CURSE);
        left.setDamage(1000); right.setDamage(1000);
        Item output = inventory(left, right).getResult();
        assertTrue(output.hasEnchantment(Enchantment.ID_BINDING_CURSE));
        assertTrue(output.hasEnchantment(Enchantment.ID_VANISHING_CURSE));
        assertTrue(output.getDamage() < 1000);
        assertTrue(inventory(left, Item.get(Item.DIAMOND_SWORD)).getResult().isNull());
    }

    @Test void onlyRemovedEnchantmentsAwardExperience() {
        Item curse = enchanted(Item.ENCHANTED_BOOK, Enchantment.ID_VANISHING_CURSE);
        assertEquals(0, GrindstoneInventory.getExperience(curse, Item.get(Item.AIR)));
        Item regular = enchanted(Item.ENCHANTED_BOOK, Enchantment.ID_DURABILITY);
        int maximum = regular.getEnchantments()[0].getMinEnchantAbility(1);
        for (int i = 0; i < 20; i++) {
            int experience = GrindstoneInventory.getExperience(regular, curse);
            assertTrue(experience >= (maximum + 1) / 2 && experience <= maximum);
        }
    }

    private static void field(GrindstoneTransaction transaction, String name, Item value) throws Exception {
        var field = GrindstoneTransaction.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(transaction, value);
    }

    @Test void transactionAcceptsRetainedCurseRejectsAlteredNbtAndAwardsOnce() throws Exception {
        Player player = mock(Player.class, RETURNS_DEEP_STUBS);
        Item input = enchanted(Item.DIAMOND_PICKAXE, Enchantment.ID_DURABILITY, Enchantment.ID_VANISHING_CURSE);
        GrindstoneInventory inventory = inventory(input, Item.get(Item.AIR));
        when(player.getWindowById(Player.GRINDSTONE_WINDOW_ID)).thenReturn(inventory);
        Level level = mock(Level.class);
        when(player.getLevel()).thenReturn(level);
        when(inventory.getHolder()).thenReturn(new FakeBlockMenu(inventory, new Position(1, 64, 1, level)));
        GrindstoneTransaction transaction = spy(new GrindstoneTransaction(player, List.of()));
        doReturn(true).when(transaction).matchItems();
        InventoryAction action = mock(InventoryAction.class);
        when(action.onPreExecute(player)).thenReturn(true);
        when(action.execute(player)).thenReturn(true);
        transaction.getActionList().add(action);
        field(transaction, "equipmentItem", input);
        field(transaction, "outputItem", inventory.getResult());
        assertTrue(transaction.canExecute());
        Item forged = inventory.getResult(); forged.setCustomName("unexpected");
        field(transaction, "outputItem", forged);
        assertFalse(transaction.canExecute());
        field(transaction, "outputItem", inventory.getResult());
        assertTrue(transaction.execute());
        assertFalse(transaction.execute());
        verify(level, times(1)).dropExpOrb(any(), anyInt());
    }
}
