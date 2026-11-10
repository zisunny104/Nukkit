package cn.nukkit.block;

import cn.nukkit.item.Item;

import cn.nukkit.Player;

public class BlockWaxedExposedCopperChest extends BlockChest implements CopperFamily {

    public BlockWaxedExposedCopperChest() {
        this(0);
    }

    public BlockWaxedExposedCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_EXPOSED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Waxed Exposed Copper Chest";
    }

    @Override
    protected boolean canPairWith(Block other) {
        return other.getId() == this.getId();
    }

    @Override
    public int[] getFamilyIds() {
        return CopperFamilies.CHEST;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        return CopperFamily.super.onActivate(item, player) || super.onActivate(item, player);
    }

    @Override
    public int onUpdate(int type) {
        int result = CopperFamily.super.onUpdate(type);
        return result != 0 ? result : super.onUpdate(type);
    }
}
