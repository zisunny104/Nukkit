package cn.nukkit.item;

public class ItemStoneSpear extends ItemTool {

    public ItemStoneSpear() {
        this(0, 1);
    }

    public ItemStoneSpear(Integer meta) {
        this(meta, 1);
    }

    public ItemStoneSpear(Integer meta, int count) {
        super(STONE_SPEAR, meta, count, "Stone Spear");
    }

    @Override
    public int getMaxDurability() {
        return 130;
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
        return 5;
    }
}
