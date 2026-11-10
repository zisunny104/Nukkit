package cn.nukkit.block;

public class BlockWaxedCopperGolemStatue extends BlockTransparentMeta {

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
}
