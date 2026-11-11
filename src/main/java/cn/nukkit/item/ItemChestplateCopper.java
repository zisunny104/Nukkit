package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemChestplateCopper extends ItemArmor {

    public ItemChestplateCopper() {
        this(0, 1);
    }

    public ItemChestplateCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemChestplateCopper(Integer meta, int count) {
        super(COPPER_CHESTPLATE, meta, count, "Copper Chestplate");
    }

    @Override
    public int getTier() {
        return ItemArmor.TIER_OTHER;
    }

    @Override
    public boolean isChestplate() {
        return true;
    }

    @Override
    public int getArmorPoints() {
        return 4;
    }

    @Override
    public int getMaxDurability() {
        return 80;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
