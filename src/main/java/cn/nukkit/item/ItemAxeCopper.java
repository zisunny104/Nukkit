package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemAxeCopper extends ItemTool {

    public ItemAxeCopper() {
        this(0, 1);
    }

    public ItemAxeCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemAxeCopper(Integer meta, int count) {
        super(COPPER_AXE, meta, count, "Copper Axe");
    }

    @Override
    public int getMaxDurability() {
        return 190;
    }

    @Override
    public boolean isAxe() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public int getAttackDamage() {
        return 4;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
