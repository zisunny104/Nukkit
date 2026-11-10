package cn.nukkit.block;

public class BlockExposedCopperGolemStatue extends BlockTransparentMeta {

    public BlockExposedCopperGolemStatue() {
        this(0);
    }

    public BlockExposedCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return EXPOSED_COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Exposed Copper Golem Statue";
    }
}
