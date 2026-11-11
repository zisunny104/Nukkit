package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemSwordCopper extends ItemTool {

    public ItemSwordCopper() {
        this(0, 1);
    }

    public ItemSwordCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemSwordCopper(Integer meta, int count) {
        super(COPPER_SWORD, meta, count, "Copper Sword");
    }

    @Override
    public int getMaxDurability() {
        return 190;
    }

    @Override
    public boolean isSword() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public int getAttackDamage() {
        return 5;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
