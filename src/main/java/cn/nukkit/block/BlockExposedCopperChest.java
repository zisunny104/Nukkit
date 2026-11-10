package cn.nukkit.block;

public class BlockExposedCopperChest extends BlockTransparentMeta {

    public BlockExposedCopperChest() {
        this(0);
    }

    public BlockExposedCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return EXPOSED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Exposed Copper Chest";
    }
}
