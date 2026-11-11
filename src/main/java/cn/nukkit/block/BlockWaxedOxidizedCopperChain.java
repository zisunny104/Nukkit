package cn.nukkit.block;

public class BlockWaxedOxidizedCopperChain extends BlockTransparentMeta {

    public BlockWaxedOxidizedCopperChain() {
        this(0);
    }

    public BlockWaxedOxidizedCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_OXIDIZED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Waxed Oxidized Copper Chain";
    }
}
