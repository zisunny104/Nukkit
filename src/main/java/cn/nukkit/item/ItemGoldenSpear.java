package cn.nukkit.item;

public class ItemGoldenSpear extends ItemTool {

    public ItemGoldenSpear() {
        this(0, 1);
    }

    public ItemGoldenSpear(Integer meta) {
        this(meta, 1);
    }

    public ItemGoldenSpear(Integer meta, int count) {
        super(GOLDEN_SPEAR, meta, count, "Golden Spear");
    }

    @Override
    public int getMaxDurability() {
        return 30;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_GOLD;
    }

    @Override
    public int getAttackDamage() {
        return 1;
    }

    @Override
    public int getEnchantAbility() {
        return 22;
    }
}
