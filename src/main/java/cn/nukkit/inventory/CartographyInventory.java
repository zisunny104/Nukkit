package cn.nukkit.inventory;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;

/** Uses the existing repair transaction route with the Bedrock cartography UI slots. */
public class CartographyInventory extends AnvilInventory {
    public static final int INPUT_UI_SLOT = 12;
    public static final int MATERIAL_UI_SLOT = 13;
    private final PlayerUIInventory ui;

    public CartographyInventory(PlayerUIInventory ui, Position position) {
        super(ui, position, InventoryType.CARTOGRAPHY, INPUT_UI_SLOT);
        this.ui = ui;
    }

    private int uiSlot(int slot) {
        return slot == 2 ? CREATED_ITEM_OUTPUT_UI_SLOT : INPUT_UI_SLOT + slot;
    }

    @Override public Item getItem(int slot) { return ui.getItem(uiSlot(slot)); }
    @Override public Item getItemFast(int slot) { return ui.getItemFast(uiSlot(slot)); }
    @Override public boolean setItem(int slot, Item item, boolean send) { return ui.setItem(uiSlot(slot), item, send); }
    @Override public void sendSlot(int slot, Player... players) { ui.sendSlot(uiSlot(slot), players); }
    @Override public Item getOutputSlot() { return getItem(2); }

    @Override public java.util.Map<Integer, Item> getContents() {
        java.util.Map<Integer, Item> contents = new java.util.HashMap<>();
        for (int slot = 0; slot < 2; slot++) {
            Item item = getItem(slot);
            if (!item.isNull()) contents.put(slot, item);
        }
        return contents;
    }
}
