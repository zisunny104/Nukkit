package cn.nukkit.block;

import cn.nukkit.item.Item;

import cn.nukkit.Player;

public class BlockWaxedCopperGolemStatue extends BlockGolemStatue implements CopperFamily {

    public BlockWaxedCopperGolemStatue() {
        this(0);
    }

    public BlockWaxedCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Waxed Copper Golem Statue";
    }

    @Override
    public int[] getFamilyIds() {
        return CopperFamilies.GOLEM_STATUE;
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
