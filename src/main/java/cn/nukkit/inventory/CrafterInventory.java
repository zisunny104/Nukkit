package cn.nukkit.inventory;

import cn.nukkit.blockentity.BlockEntityCrafter;
import cn.nukkit.item.Item;

public class CrafterInventory extends ContainerInventory {

    public CrafterInventory(BlockEntityCrafter crafter) {
        super(crafter, InventoryType.CRAFTER);
    }

    @Override
    public BlockEntityCrafter getHolder() {
        return (BlockEntityCrafter) super.getHolder();
    }

    @Override
    public void onSlotChange(int index, Item before, boolean send) {
        super.onSlotChange(index, before, send);

        this.getHolder().chunk.setChanged();
    }
}
