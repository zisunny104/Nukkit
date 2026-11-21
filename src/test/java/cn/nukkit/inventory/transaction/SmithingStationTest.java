package cn.nukkit.inventory.transaction;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.inventory.*;
import cn.nukkit.inventory.transaction.action.InventoryAction;
import cn.nukkit.item.*;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.nbt.tag.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SmithingStationTest {
    @BeforeAll static void initialize() {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        Enchantment.init();
    }

    @Test void smithingCopiesDataAndNeverExposesItsSharedResult() {
        Item equipment = Item.get(Item.DIAMOND_PICKAXE, 650);
        equipment.setCustomName("named");
        equipment.addEnchantment(Enchantment.getEnchantment(Enchantment.ID_MENDING));
        Item template = Item.get(Item.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        SmithingRecipe recipe = new SmithingRecipe("upgrade", 0, List.of(equipment, Item.get(Item.NETHERITE_INGOT), template), Item.get(Item.NETHERITE_PICKAXE));
        Item output = recipe.getFinalResult(equipment, template);
        assertEquals(650, output.getDamage());
        assertEquals("named", output.getCustomName());
        assertTrue(output.hasEnchantment(Enchantment.ID_MENDING));
        output.setCustomName("changed");
        assertEquals("named", equipment.getCustomName());
        Item shared = recipe.getResult();
        shared.setCustomName("changed");
        assertFalse(recipe.getResult().hasCustomName());
    }

    @Test void smithingRequiresActualTemplateConsumption() {
        Player player = mock(Player.class, RETURNS_DEEP_STUBS);
        SmithingTransaction transaction = new SmithingTransaction(player, List.of());
        Item template = Item.get(Item.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        transaction.templateItem = template;
        InventoryAction virtual = action(Item.get(Item.AIR), template);
        transaction.actions.add(virtual);
        assertFalse(transaction.matchItems());
        transaction.actions.add(action(template, Item.get(Item.AIR)));
        assertTrue(transaction.matchItems());
    }

    private InventoryAction action(Item source, Item target) {
        InventoryAction action = mock(InventoryAction.class);
        when(action.isValid(any())).thenReturn(true);
        when(action.getSourceItem()).thenAnswer(call -> source.clone());
        when(action.getSourceItemUnsafe()).thenReturn(source);
        when(action.getTargetItem()).thenAnswer(call -> target.clone());
        when(action.getTargetItemUnsafe()).thenReturn(target);
        return action;
    }
}
