package cn.nukkit.item;

public class ItemCopperSpear extends ItemTool {

    public ItemCopperSpear() {
        this(0, 1);
    }

    public ItemCopperSpear(Integer meta) {
        this(meta, 1);
    }

    public ItemCopperSpear(Integer meta, int count) {
        super(COPPER_SPEAR, meta, count, "Copper Spear");
    }

    @Override
    public int getMaxDurability() {
        return 190;
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
        return 13;
    }
}
