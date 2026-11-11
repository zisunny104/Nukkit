package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemBootsCopper extends ItemArmor {

    public ItemBootsCopper() {
        this(0, 1);
    }

    public ItemBootsCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemBootsCopper(Integer meta, int count) {
        super(COPPER_BOOTS, meta, count, "Copper Boots");
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_OTHER;
    }

    @Override
    public boolean isBoots() {
        return true;
    }

    @Override
    public int getArmorPoints() {
        return 1;
    }

    @Override
    public int getMaxDurability() {
        return 65;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
