package cn.nukkit.item;

public class ItemNetheriteHorseArmor extends Item {

    public ItemNetheriteHorseArmor() {
        this(0, 1);
    }

    public ItemNetheriteHorseArmor(Integer meta) {
        this(meta, 1);
    }

    public ItemNetheriteHorseArmor(Integer meta, int count) {
        super(NETHERITE_HORSE_ARMOR, meta, count, "Netherite Horse Armor");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
