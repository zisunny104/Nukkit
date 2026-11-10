package cn.nukkit.block;

import cn.nukkit.item.Item;

import cn.nukkit.Player;

public class BlockCopperChain extends BlockTransparentMeta implements CopperFamily {

    public BlockCopperChain() {
        this(0);
    }

    public BlockCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Copper Chain";
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
