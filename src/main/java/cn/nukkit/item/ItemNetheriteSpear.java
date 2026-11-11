package cn.nukkit.item;

public class ItemNetheriteSpear extends ItemTool {

    public ItemNetheriteSpear() {
        this(0, 1);
    }

    public ItemNetheriteSpear(Integer meta) {
        this(meta, 1);
    }

    public ItemNetheriteSpear(Integer meta, int count) {
        super(NETHERITE_SPEAR, meta, count, "Netherite Spear");
    }

    @Override
    public int getMaxDurability() {
        return 2030;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_NETHERITE;
    }

    @Override
    public int getAttackDamage() {
        return 5;
    }

    @Override
    public int getEnchantAbility() {
        return 15;
    }
}
