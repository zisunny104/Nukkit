package cn.nukkit.inventory;

import cn.nukkit.item.Item;
import java.util.List;

/** A single-ingredient recipe advertised for the stonecutter UI. */
public class StonecutterRecipe extends ShapelessRecipe {
    public StonecutterRecipe(String recipeId, int priority, Item output, Item input) {
        super(recipeId, priority, output, List.of(input));
    }
}
