package cn.nukkit.test;

import cn.nukkit.block.Block;
import cn.nukkit.inventory.*;
import cn.nukkit.inventory.transaction.RepairItemTransaction;
import cn.nukkit.item.*;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.network.protocol.CraftingDataPacket;
import cn.nukkit.utils.Config;
import cn.nukkit.Player;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkstationRecipeTest {
    @Test @SuppressWarnings("unchecked")
    void bundledStationRecipesLoadAndMatchTheirExactOutputs() {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        Enchantment.init();
        CraftingManager manager = new CraftingManager();
        var data = new Config(Config.YAML).loadFromStream(
                getClass().getResourceAsStream("/recipes.json")).getRootSection();
        long expected = ((List<Map<String,Object>>) data.get("recipes")).stream()
                .filter(recipe -> "stonecutter".equals(recipe.get("block"))).count();
        var stonecutting = manager.getRecipes().stream()
                .filter(recipe -> recipe instanceof StonecutterRecipe).map(recipe -> (StonecutterRecipe)recipe).toList();
        assertTrue(expected > 0);
        assertEquals(expected, stonecutting.size());
        for (StonecutterRecipe recipe : stonecutting) {
            Item input = recipe.getIngredientList().get(0);
            assertNotNull(manager.matchRecipe(new ArrayList<>(List.of(input.clone())),
                    recipe.getResult(), List.of()), recipe.getRecipeId());
        }
        assertEquals(Item.IRON_INGOT, manager.matchFurnaceRecipe(Item.get(Item.IRON_ORE)).getResult().getId());
        assertEquals(Item.IRON_INGOT, manager.matchBlastFurnaceRecipe(Item.get(Item.IRON_ORE)).getResult().getId());
        assertNotNull(manager.matchSmokerRecipe(Item.get(Item.RAW_BEEF)));
        assertNotNull(manager.matchCampfireRecipe(Item.get(Item.RAW_BEEF)));
        int mixes = 0;
        for (var mix : (List<Map<String,Object>>)data.get("potionMixes")) {
            Item input = item((String)mix.get("inputId"), (int)mix.get("inputMeta"));
            Item ingredient = item((String)mix.get("reagentId"), (int)mix.get("reagentMeta"));
            Item output = item((String)mix.get("outputId"), (int)mix.get("outputMeta"));
            BrewingRecipe recipe = manager.matchBrewingRecipe(ingredient, input);
            assertNotNull(recipe, mix.toString());
            assertTrue(output.equals(recipe.getResult(), true, true), mix.toString());
            mixes++;
        }
        assertTrue(mixes > 0);
        for (var mix : (List<Map<String,Object>>)data.get("containerMixes")) {
            var recipe = manager.matchContainerRecipe(item((String)mix.get("reagentId"), 0),
                    item((String)mix.get("inputId"), 0));
            assertNotNull(recipe, mix.toString());
            assertEquals(item((String)mix.get("outputId"), 0).getId(), recipe.getResult().getId());
        }
        CraftingDataPacket packet = new CraftingDataPacket();
        packet.addShapelessRecipe(stonecutting.get(0));
        packet.encode();
        assertTrue(new String(packet.getBuffer(), StandardCharsets.ISO_8859_1).contains("stonecutter"));
        System.out.println("STONECUTTER_CHECKED=" + stonecutting.size() + " POTION_MIXES_CHECKED=" + mixes);
    }

    @Test void anvilRejectsOutputThatDropsExpectedEnchantments() throws Exception {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        Enchantment.init();
        Item input = Item.get(Item.DIAMOND_PICKAXE);
        Item material = Item.get(Item.ENCHANTED_BOOK);
        material.addEnchantment(Enchantment.getEnchantment(Enchantment.ID_DURABILITY));
        Item output = input.clone();
        output.setRepairCost(1);
        RepairItemTransaction transaction = new RepairItemTransaction(mock(Player.class, RETURNS_DEEP_STUBS), new ArrayList<>());
        for (var entry : Map.of("inputItem", input, "materialItem", material, "outputItem", output).entrySet()) {
            var field = RepairItemTransaction.class.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(transaction, entry.getValue());
        }
        var check = RepairItemTransaction.class.getDeclaredMethod("checkRecipeValid");
        check.setAccessible(true);
        assertEquals(false, check.invoke(transaction));
    }

    private static Item item(String identifier, int damage) {
        var legacy = RuntimeItems.getMapping().fromIdentifier(identifier);
        assertNotNull(legacy, identifier);
        return Item.get(legacy.getLegacyId(), damage);
    }
}
