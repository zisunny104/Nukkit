package cn.nukkit.item;

public class ItemGoldenNautilusArmor extends Item {

    public ItemGoldenNautilusArmor() {
        this(0, 1);
    }

    public ItemGoldenNautilusArmor(Integer meta) {
        this(meta, 1);
    }

    public ItemGoldenNautilusArmor(Integer meta, int count) {
        super(GOLDEN_NAUTILUS_ARMOR, meta, count, "Golden Nautilus Armor");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
