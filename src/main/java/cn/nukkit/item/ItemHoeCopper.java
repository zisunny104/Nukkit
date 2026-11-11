package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemHoeCopper extends ItemTool {

    public ItemHoeCopper() {
        this(0, 1);
    }

    public ItemHoeCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemHoeCopper(Integer meta, int count) {
        super(COPPER_HOE, meta, count, "Copper Hoe");
    }

    @Override
    public int getMaxDurability() {
        return 190;
    }

    @Override
    public boolean isHoe() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public int getEnchantAbility() {
        return 8;
    }
}
