package cn.nukkit.item;

public class ItemIronSpear extends ItemTool {

    public ItemIronSpear() {
        this(0, 1);
    }

    public ItemIronSpear(Integer meta) {
        this(meta, 1);
    }

    public ItemIronSpear(Integer meta, int count) {
        super(IRON_SPEAR, meta, count, "Iron Spear");
    }

    @Override
    public int getMaxDurability() {
        return 250;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_IRON;
    }

    @Override
    public int getAttackDamage() {
        return 3;
    }

    @Override
    public int getEnchantAbility() {
        return 14;
    }
}
