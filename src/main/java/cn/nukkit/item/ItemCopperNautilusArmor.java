package cn.nukkit.item;

public class ItemCopperNautilusArmor extends Item {

    public ItemCopperNautilusArmor() {
        this(0, 1);
    }

    public ItemCopperNautilusArmor(Integer meta) {
        this(meta, 1);
    }

    public ItemCopperNautilusArmor(Integer meta, int count) {
        super(COPPER_NAUTILUS_ARMOR, meta, count, "Copper Nautilus Armor");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
