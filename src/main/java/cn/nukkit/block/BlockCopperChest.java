package cn.nukkit.block;

public class BlockCopperChest extends BlockTransparentMeta {

    public BlockCopperChest() {
        this(0);
    }

    public BlockCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Copper Chest";
    }
}
