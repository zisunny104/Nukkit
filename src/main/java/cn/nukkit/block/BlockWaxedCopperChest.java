package cn.nukkit.block;

public class BlockWaxedCopperChest extends BlockChest {

    public BlockWaxedCopperChest() {
        this(0);
    }

    public BlockWaxedCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Waxed Copper Chest";
    }

    @Override
    protected boolean canPairWith(Block other) {
        return other.getId() == this.getId();
    }
}
