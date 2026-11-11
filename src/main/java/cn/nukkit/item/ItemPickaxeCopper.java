package cn.nukkit.item;

/**
 * @author MagicDroidX
 * Nukkit Project
 */
public class ItemPickaxeCopper extends ItemTool {

    public ItemPickaxeCopper() {
        this(0, 1);
    }

    public ItemPickaxeCopper(Integer meta) {
        this(meta, 1);
    }

    public ItemPickaxeCopper(Integer meta, int count) {
        super(COPPER_PICKAXE, meta, count, "Copper Pickaxe");
    }

    @Override
    public int getMaxDurability() {
        return 190;
    }

    @Override
    public boolean isPickaxe() {
        return true;
    }

    @Override
    public int getTier() {
        return ItemTool.TIER_STONE;
    }

    @Override
    public int getAttackDamage() {
        return 3;
    }

    @Override
    public int getEnchantAbility() {
        return 13;
    }
}
