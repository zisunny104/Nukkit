package cn.nukkit.item;

public class ItemDiamondNautilusArmor extends Item {

    public ItemDiamondNautilusArmor() {
        this(0, 1);
    }

    public ItemDiamondNautilusArmor(Integer meta) {
        this(meta, 1);
    }

    public ItemDiamondNautilusArmor(Integer meta, int count) {
        super(DIAMOND_NAUTILUS_ARMOR, meta, count, "Diamond Nautilus Armor");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
