package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemHelmetCopper extends ItemArmor {

    public ItemHelmetCopper() {
        this(0, 1);
    }

    public ItemHelmetCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemHelmetCopper(Integer meta, int count) {
        super(COPPER_HELMET, meta, count, "Copper Helmet");
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_OTHER;
    }

    @Override
    public boolean isHelmet() {
        return true;
    }

    @Override
    public int getArmorPoints() {
        return 2;
    }

    @Override
    public int getMaxDurability() {
        return 55;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
