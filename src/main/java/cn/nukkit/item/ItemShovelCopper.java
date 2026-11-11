package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemShovelCopper extends ItemTool {

    public ItemShovelCopper() {
        this(0, 1);
    }

    public ItemShovelCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemShovelCopper(Integer meta, int count) {
        super(COPPER_SHOVEL, meta, count, "Copper Shovel");
    }

    @Override
    public int getMaxDurability() {
        return 190;
    }

    @Override
    public boolean isShovel() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public int getAttackDamage() {
        return 2;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
