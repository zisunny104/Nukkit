package cn.nukkit.block;

public class BlockWaxedExposedCopperChain extends BlockTransparentMeta {

    public BlockWaxedExposedCopperChain() {
        this(0);
    }

    public BlockWaxedExposedCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_EXPOSED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Waxed Exposed Copper Chain";
    }
}
