package cn.nukkit.inventory;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemMap;
import cn.nukkit.nbt.tag.CompoundTag;

/** Deterministic map operations; a new map identity is allocated only when a transaction succeeds. */
public final class CartographyRecipe {
    private CartographyRecipe() { }

    public static Item result(Item input, Item material) {
        if (input.isNull() || material.isNull() || input.getCount() != 1) return null;
        Item output = input.clone();
        if (input.getId() == Item.EMPTY_MAP && material.getId() == Item.COMPASS && input.getDamage() != 2) {
            output.setDamage(2);
            return output;
        }
        if (!(input instanceof ItemMap map) || map.getMapId() == 0) return null;
        if (material.getId() == Item.EMPTY_MAP) {
            output.setCount(2);
            return output;
        }
        if (material.getId() == Item.COMPASS && input.getDamage() != 2) {
            output.setDamage(2);
            return output;
        }
        if (map.isMapLocked()) return null;
        CompoundTag tag = input.getNamedTag().clone();
        if (material.getId() == Item.GLASS_PANE) {
            if (!tag.contains("Colors")) return null; // A lock requires an existing image, including legacy maps.
            output.setNamedTag(tag.putBoolean("map_locked", true));
            return output;
        }
        if (material.getId() == Item.PAPER && map.hasMapOrigin() && map.getMapScale() < 4) {
            output.setNamedTag(tag.remove("Colors").putInt("map_scale", map.getMapScale() + 1));
            ((ItemMap) output).invalidateImage();
            return output;
        }
        return null;
    }

    public static boolean matches(Item expected, Item supplied) {
        if (expected == null || expected.getId() != supplied.getId() || expected.getDamage() != supplied.getDamage()
                || expected.getCount() != supplied.getCount()) return false;
        CompoundTag a = expected.getNamedTag(), b = supplied.getNamedTag();
        a = a == null ? new CompoundTag() : a.clone();
        b = b == null ? new CompoundTag() : b.clone();
        // These fields are always reconstructed from the server's inputs, never accepted from the client.
        for (String key : new String[]{"map_uuid", "map_name_index", "map_scale", "map_locked", "Colors"}) {
            a.remove(key);
            b.remove(key);
        }
        return a.equals(b);
    }

    public static boolean needsNewIdentity(Item material) {
        return material.getId() == Item.PAPER || material.getId() == Item.GLASS_PANE;
    }
}
