package cn.nukkit.block;

import cn.nukkit.item.Item;

import cn.nukkit.Player;

public class BlockExposedCopperChain extends BlockTransparentMeta implements CopperFamily {

    public BlockExposedCopperChain() {
        this(0);
    }

    public BlockExposedCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return EXPOSED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Exposed Copper Chain";
    }

    @Override
    public int[] getFamilyIds() {
        return CopperFamilies.CHAIN;
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
