package cn.nukkit.block;

public class BlockWeatheredCopperGolemStatue extends BlockTransparentMeta {

    public BlockWeatheredCopperGolemStatue() {
        this(0);
    }

    public BlockWeatheredCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WEATHERED_COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Weathered Copper Golem Statue";
    }
}
