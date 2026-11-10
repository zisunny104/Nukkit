package cn.nukkit.block;

public class BlockWaxedExposedCopperChest extends BlockTransparentMeta {

    public BlockWaxedExposedCopperChest() {
        this(0);
    }

    public BlockWaxedExposedCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_EXPOSED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Waxed Exposed Copper Chest";
    }
}
