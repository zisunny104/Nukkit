package cn.nukkit.item;

public class ItemNetheriteNautilusArmor extends Item {

    public ItemNetheriteNautilusArmor() {
        this(0, 1);
    }

    public ItemNetheriteNautilusArmor(Integer meta) {
        this(meta, 1);
    }

    public ItemNetheriteNautilusArmor(Integer meta, int count) {
        super(NETHERITE_NAUTILUS_ARMOR, meta, count, "Netherite Nautilus Armor");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
