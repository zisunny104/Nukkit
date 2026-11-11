package cn.nukkit.item;

public class ItemWoodenSpear extends ItemTool {

    public ItemWoodenSpear() {
        this(0, 1);
    }

    public ItemWoodenSpear(Integer meta) {
        this(meta, 1);
    }

    public ItemWoodenSpear(Integer meta, int count) {
        super(WOODEN_SPEAR, meta, count, "Wooden Spear");
    }

    @Override
    public int getMaxDurability() {
        return 60;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_WOODEN;
    }

    @Override
    public int getAttackDamage() {
        return 1;
    }

    @Override
    public int getEnchantAbility() {
        return 15;
    }
}
