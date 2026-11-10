package cn.nukkit.block;

public class BlockCopperGolemStatue extends BlockTransparentMeta {

    public BlockCopperGolemStatue() {
        this(0);
    }

    public BlockCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Copper Golem Statue";
    }
}
