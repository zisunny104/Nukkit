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

class CartographyRecipeTest {
    @BeforeAll static void initialize() {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        Enchantment.init();
    }

    @Test void legacyMapCopyAndLockPreserveTheOriginalImage() {
        ItemMap original = (ItemMap)Item.get(Item.MAP);
        original.setNamedTag(new CompoundTag().putLong("map_uuid", 12).putByteArray("Colors", new byte[]{1, 2, 3}));
        Item copy = CartographyRecipe.result(original, Item.get(Item.EMPTY_MAP));
        assertNotNull(copy);
        assertEquals(2, copy.getCount());
        assertArrayEquals(new byte[]{1, 2, 3}, copy.getNamedTag().getByteArray("Colors"));
        assertEquals(12, ((ItemMap)copy).getMapId());
        assertNull(CartographyRecipe.result(original, Item.get(Item.PAPER)));
        Item locked = CartographyRecipe.result(original, Item.get(Item.GLASS_PANE));
        assertTrue(((ItemMap)locked).isMapLocked());
        assertFalse(original.isMapLocked());
        assertArrayEquals(original.getNamedTag().getByteArray("Colors"), locked.getNamedTag().getByteArray("Colors"));
    }

    @Test void positionedMapScalesAndLockedMapCannotScale() {
        ItemMap original = (ItemMap)Item.get(Item.MAP);
        original.setNamedTag(new CompoundTag().putLong("map_uuid", 12).putByteArray("Colors", new byte[]{1}));
        original.setMapOrigin(64, 64, 0);
        ItemMap scaled = (ItemMap)CartographyRecipe.result(original, Item.get(Item.PAPER));
        assertEquals(1, scaled.getMapScale());
        assertFalse(scaled.getNamedTag().contains("Colors"));
        assertEquals(0, original.getMapScale());
        scaled.setNamedTag(scaled.getNamedTag().putInt("map_scale", 4));
        assertNull(CartographyRecipe.result(scaled, Item.get(Item.PAPER)));
        ItemMap locked = (ItemMap)CartographyRecipe.result(original, Item.get(Item.GLASS_PANE));
        assertNull(CartographyRecipe.result(locked, Item.get(Item.PAPER)));
    }

}
