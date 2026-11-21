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

class LoomStationTest {
    @BeforeAll static void initialize() {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        Enchantment.init();
    }

    @Test void loomRejectsForgedColorAndAlteredExistingData() {
        Item banner = Item.get(Item.BANNER);
        Item dye = Item.get(Item.DYE, ItemDye.RED);
        Item output = banner.clone();
        ListTag<CompoundTag> patterns = new ListTag<>("Patterns");
        patterns.add(new CompoundTag().putString("Pattern", "bs")
                .putInt("Color", ((ItemDye)dye).getDyeColor().getDyeData()));
        output.setNamedTag(new CompoundTag().putList(patterns));
        assertTrue(LoomTransaction.validPatternAddition(banner, dye, Item.get(Item.AIR), output));
        Item forged = output.clone();
        forged.setCustomName("forged");
        assertFalse(LoomTransaction.validPatternAddition(banner, dye, Item.get(Item.AIR), forged));
        var bad = output.getNamedTag().clone();
        bad.getList("Patterns", CompoundTag.class).get(0).putInt("Color", -1);
        forged.setNamedTag(bad);
        assertFalse(LoomTransaction.validPatternAddition(banner, dye, Item.get(Item.AIR), forged));
    }

}
