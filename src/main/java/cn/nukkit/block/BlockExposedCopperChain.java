package cn.nukkit.block;

public class BlockExposedCopperChain extends BlockTransparentMeta {

    public BlockExposedCopperChain() {
        this(0);
    }

    public BlockExposedCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return EXPOSED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Exposed Copper Chain";
    }
}
