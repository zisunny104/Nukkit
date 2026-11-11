package cn.nukkit.item;

public class ItemDiamondSpear extends ItemTool {

    public ItemDiamondSpear() {
        this(0, 1);
    }

    public ItemDiamondSpear(Integer meta) {
        this(meta, 1);
    }

    public ItemDiamondSpear(Integer meta, int count) {
        super(DIAMOND_SPEAR, meta, count, "Diamond Spear");
    }

    @Override
    public int getMaxDurability() {
        return ItemTool.DURABILITY_DIAMOND;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_DIAMOND;
    }

    @Override
    public int getAttackDamage() {
        return 4;
    }
}
