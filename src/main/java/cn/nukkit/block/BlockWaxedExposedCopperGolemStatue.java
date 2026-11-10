package cn.nukkit.block;

public class BlockWaxedExposedCopperGolemStatue extends BlockTransparentMeta {

    public BlockWaxedExposedCopperGolemStatue() {
        this(0);
    }

    public BlockWaxedExposedCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_EXPOSED_COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Waxed Exposed Copper Golem Statue";
    }
}
