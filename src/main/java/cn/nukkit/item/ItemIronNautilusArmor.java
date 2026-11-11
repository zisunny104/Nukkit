package cn.nukkit.item;

public class ItemIronNautilusArmor extends Item {

    public ItemIronNautilusArmor() {
        this(0, 1);
    }

    public ItemIronNautilusArmor(Integer meta) {
        this(meta, 1);
    }

    public ItemIronNautilusArmor(Integer meta, int count) {
        super(IRON_NAUTILUS_ARMOR, meta, count, "Iron Nautilus Armor");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
