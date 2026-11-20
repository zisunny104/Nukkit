package cn.nukkit.test;

import cn.nukkit.block.Block;
import cn.nukkit.inventory.transaction.RepairItemTransaction;
import cn.nukkit.item.Item;
import cn.nukkit.item.RuntimeItems;
import cn.nukkit.item.enchantment.Enchantment;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class AnvilBookCostTest {
    @Test
    void applyingBooksChargesForResultLevelIncludingExistingStrongerEnchantments() throws Exception {
        Block.init();
        RuntimeItems.init();
        Item.init();
        Enchantment.init();
        for (int[] levels : new int[][]{{0, 1, 1, 0, Enchantment.ID_DURABILITY}, {0, 3, 3, 0, Enchantment.ID_DURABILITY}, {1, 1, 2, 0, Enchantment.ID_DURABILITY}, {3, 1, 3, 0, Enchantment.ID_DURABILITY}, {3, 3, 3, 0, Enchantment.ID_DURABILITY}, {0, 1, 1, 750, Enchantment.ID_MENDING}, {1, 1, 1, 750, Enchantment.ID_MENDING}}) {
            Item input = Item.get(Item.DIAMOND_PICKAXE, levels[3]);
            if (levels[0] > 0) input.addEnchantment(Enchantment.getEnchantment(levels[4]).setLevel(levels[0]));
            Item book = Item.get(Item.ENCHANTED_BOOK);
            book.addEnchantment(Enchantment.getEnchantment(levels[4]).setLevel(levels[1]));
            Item output = input.clone();
            output.addEnchantment(Enchantment.getEnchantment(levels[4]).setLevel(levels[2]));
            output.setRepairCost(1);
            RepairItemTransaction transaction = new RepairItemTransaction(null, new ArrayList<>());
            assign(transaction, "inputItem", input);
            assign(transaction, "materialItem", book);
            assign(transaction, "outputItem", output);
            var check = RepairItemTransaction.class.getDeclaredMethod("checkRecipeValid");
            check.setAccessible(true);
            assertEquals(true, check.invoke(transaction));
            assertEquals(levels[2] * (levels[4] == Enchantment.ID_MENDING ? 2 : 1), transaction.getCost());
        }
    }

    private void assign(Object transaction, String name, Item item) throws Exception {
        var field = RepairItemTransaction.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(transaction, item);
    }
}
