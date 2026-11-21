package cn.nukkit.test;

import cn.nukkit.block.Block;
import cn.nukkit.inventory.*;
import cn.nukkit.item.*;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.network.protocol.CraftingDataPacket;
import cn.nukkit.utils.Config;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class SpecialDyeRecipeTest {
    @Test @SuppressWarnings("unchecked")
    void allSpecialDyeRecipesPreserveSourceDataAndRejectChangedOrDroppedContents() {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        CraftingManager manager = new CraftingManager();
        var root = new Config(Config.YAML).loadFromStream(getClass().getResourceAsStream("/recipes.json")).getRootSection();
        Map<String, DataShapelessRecipe> recipes = new HashMap<>();
        for (Recipe recipe : manager.getRecipes()) {
            if (recipe instanceof DataShapelessRecipe dye) recipes.put(dye.getRecipeId(), dye);
        }
        int checked = 0;
        for (var row : (List<Map<String,Object>>) root.get("recipes")) {
            if (!Integer.valueOf(5).equals(row.get("type"))) continue;
            String key = (String)row.get("id");
            var recipe = recipes.get(key);
            assertNotNull(recipe, key);
            var sourceEntry = ((List<Map<String,Object>>)row.get("input")).get(0);
            var legacy = RuntimeItems.getMapping().fromRuntime((int)sourceEntry.get("itemId"));
            List<Item> inputs = recipe.getIngredientList();
            Item source = inputs.stream().filter(item -> item.getId() == legacy.getLegacyId()).findFirst().orElseThrow();
            source.setNamedTag(new CompoundTag().putString("testContents", key));
            Item expected = recipe.getResult().clone();
            expected.setNamedTag(source.getNamedTag().clone());
            Item output = recipe.getFinalResult(inputs);
            assertNotNull(output, key);
            assertTrue(output.equals(expected, true, true), key);
            assertNotNull(manager.matchRecipe(new ArrayList<>(inputs), output, List.of()), key);
            assertNull(manager.matchRecipe(new ArrayList<>(inputs), recipe.getResult().clone(), List.of()), key);
            Item forged = output.clone();
            forged.setNamedTag(new CompoundTag().putString("testContents", "changed"));
            assertNull(manager.matchRecipe(new ArrayList<>(inputs), forged, List.of()), key);
            assertEquals(key, source.getNamedTag().getString("testContents"));
            checked++;
        }
        assertEquals(1456, checked);
        CraftingDataPacket packet = new CraftingDataPacket();
        packet.addShapelessRecipe(recipes.values().iterator().next());
        assertDoesNotThrow(packet::encode);
        assertTrue(packet.getBuffer().length > 10);
        System.out.println("DATA_RECIPES_CHECKED=" + checked);
    }
}
