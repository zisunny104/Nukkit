package cn.nukkit.block;

public class BlockWaxedCopperChain extends BlockTransparentMeta {

    public BlockWaxedCopperChain() {
        this(0);
    }

    public BlockWaxedCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Waxed Copper Chain";
    }
}
