package cn.nukkit.block;

public class BlockWaxedOxidizedCopperChest extends BlockTransparentMeta {

    public BlockWaxedOxidizedCopperChest() {
        this(0);
    }

    public BlockWaxedOxidizedCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_OXIDIZED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Waxed Oxidized Copper Chest";
    }
}
