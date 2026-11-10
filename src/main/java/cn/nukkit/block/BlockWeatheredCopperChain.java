package cn.nukkit.block;

public class BlockWeatheredCopperChain extends BlockTransparentMeta {

    public BlockWeatheredCopperChain() {
        this(0);
    }

    public BlockWeatheredCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WEATHERED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Weathered Copper Chain";
    }
}
