package cn.nukkit.test;

import cn.nukkit.Server;
import cn.nukkit.blockentity.BlockEntityBrewingStand;
import cn.nukkit.inventory.*;
import cn.nukkit.item.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrewingConsumptionTest {
    @Test void completingTwoBottlesConsumesOneIngredientAndOneFuelCharge() throws Exception {
        BlockEntityBrewingStand stand = mock(BlockEntityBrewingStand.class);
        BrewingInventory inventory = mock(BrewingInventory.class);
        Server server = mock(Server.class, RETURNS_DEEP_STUBS);
        var level = mock(cn.nukkit.level.Level.class);
        CraftingManager manager = mock(CraftingManager.class);
        Item ingredient = Item.get(Item.NETHER_WART, 0, 4);
        Item water = Item.get(Item.POTION, 0);
        BrewingRecipe recipe = new BrewingRecipe(water, ingredient, Item.get(Item.POTION, 16));
        set(stand, "inventory", inventory);
        set(stand, "server", server);
        set(stand, "fuelAmount", 1);
        set(stand, "brewTime", 1);
        when(stand.getInventory()).thenReturn(inventory);
        when(stand.getLevel()).thenReturn(level);
        when(level.getServer()).thenReturn(server);
        when(server.getCraftingManager()).thenReturn(manager);
        when(inventory.getFuel()).thenReturn(Item.get(Item.AIR));
        when(inventory.getIngredient()).thenReturn(ingredient);
        when(inventory.getItem(1)).thenReturn(water);
        when(inventory.getItem(2)).thenReturn(water);
        when(inventory.getItem(3)).thenReturn(Item.get(Item.AIR));
        when(manager.matchBrewingRecipe(any(Item.class), any(Item.class))).thenReturn(recipe);
        doCallRealMethod().when(stand).onUpdate();
        assertTrue(stand.onUpdate());
        for (int slot : new int[]{1, 2}) {
            verify(inventory).setItem(eq(slot),
                    argThat(item -> item.getId() == Item.POTION && item.getDamage() == 16 && item.getCount() == 1));
        }
        verify(inventory).setIngredient(argThat(item -> item.getCount() == 3));
        var fuel = BlockEntityBrewingStand.class.getDeclaredField("fuelAmount");
        fuel.setAccessible(true);
        assertEquals(0, fuel.getInt(stand));
    }

    private static void set(Object target, String name, Object value) throws Exception {
        for (Class<?> type = BlockEntityBrewingStand.class; type != null; type = type.getSuperclass()) {
            try {
                var field = type.getDeclaredField(name);
                field.setAccessible(true);
                field.set(target, value);
                return;
            } catch (NoSuchFieldException absent) {
                // Continue through the block entity hierarchy.
            }
        }
        throw new NoSuchFieldException(name);
    }
}
