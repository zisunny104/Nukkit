package cn.nukkit.inventory;

import cn.nukkit.item.Item;
import java.util.List;

/** Bedrock type 5 dye recipes carry data from the first declared ingredient. */
public class DataShapelessRecipe extends ShapelessRecipe {
    private final Item dataSource;

    public DataShapelessRecipe(String id, int priority, Item output, List<Item> inputs, Item dataSource) {
        super(id, priority, output, inputs);
        this.dataSource = dataSource.clone();
    }

    @Override public RecipeType getType() {
        return RecipeType.SHULKER_BOX;
    }

    @Override public Item getFinalResult(List<Item> inputs) {
        Item source = null;
        for (Item input : inputs) {
            if (!dataSource.equals(input, dataSource.hasMeta(), false)) continue;
            if (source != null && !java.util.Objects.equals(source.getNamedTag(), input.getNamedTag())) {
                return null; // Different contents cannot produce a single stack of identical results.
            }
            source = input;
        }
        if (source == null) return null;
        Item output = getResult().clone();
        output.setCompoundTag(source.getCompoundTag().clone());
        return output;
    }
}
