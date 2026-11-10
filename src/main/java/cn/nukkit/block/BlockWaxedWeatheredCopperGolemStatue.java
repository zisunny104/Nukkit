package cn.nukkit.block;

public class BlockWaxedWeatheredCopperGolemStatue extends BlockTransparentMeta {

    public BlockWaxedWeatheredCopperGolemStatue() {
        this(0);
    }

    public BlockWaxedWeatheredCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_WEATHERED_COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Waxed Weathered Copper Golem Statue";
    }
}
