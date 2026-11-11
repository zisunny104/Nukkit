package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemLeggingsCopper extends ItemArmor {

    public ItemLeggingsCopper() {
        this(0, 1);
    }

    public ItemLeggingsCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemLeggingsCopper(Integer meta, int count) {
        super(COPPER_LEGGINGS, meta, count, "Copper Leggings");
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_OTHER;
    }

    @Override
    public boolean isLeggings() {
        return true;
    }

    @Override
    public int getArmorPoints() {
        return 3;
    }

    @Override
    public int getMaxDurability() {
        return 75;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
