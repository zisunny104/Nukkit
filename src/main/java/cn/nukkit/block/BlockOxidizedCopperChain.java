package cn.nukkit.block;

public class BlockOxidizedCopperChain extends BlockTransparentMeta {

    public BlockOxidizedCopperChain() {
        this(0);
    }

    public BlockOxidizedCopperChain(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return OXIDIZED_COPPER_CHAIN;
    }

    @Override
    public String getName() {
        return "Oxidized Copper Chain";
    }
}
