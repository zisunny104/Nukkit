package cn.nukkit.item;

public class ItemCopperHorseArmor extends Item {

    public ItemCopperHorseArmor() {
        this(0, 1);
    }

    public ItemCopperHorseArmor(Integer meta) {
        this(meta, 1);
    }

    public ItemCopperHorseArmor(Integer meta, int count) {
        super(COPPER_HORSE_ARMOR, meta, count, "Copper Horse Armor");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
