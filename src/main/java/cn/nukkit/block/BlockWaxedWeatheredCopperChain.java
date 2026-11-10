package cn.nukkit.block;

public class BlockWaxedWeatheredCopperChain extends BlockTransparentMeta {

    public BlockWaxedWeatheredCopperChain() {
        this(0);
    }

    public BlockWaxedWeatheredCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_WEATHERED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Waxed Weathered Copper Chain";
    }
}
