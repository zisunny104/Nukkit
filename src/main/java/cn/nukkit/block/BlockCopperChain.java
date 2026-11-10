package cn.nukkit.block;

public class BlockCopperChain extends BlockTransparentMeta {

    public BlockCopperChain() {
        this(0);
    }

    public BlockCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Copper Chain";
    }
}
