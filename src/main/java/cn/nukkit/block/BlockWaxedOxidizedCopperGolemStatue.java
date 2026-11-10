package cn.nukkit.block;

public class BlockWaxedOxidizedCopperGolemStatue extends BlockTransparentMeta {

    public BlockWaxedOxidizedCopperGolemStatue() {
        this(0);
    }

    public BlockWaxedOxidizedCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_OXIDIZED_COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Waxed Oxidized Copper Golem Statue";
    }
}
