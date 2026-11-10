package cn.nukkit.block;

public class BlockWeatheredCopperChest extends BlockChest {

    public BlockWeatheredCopperChest() {
        this(0);
    }

    public BlockWeatheredCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WEATHERED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Weathered Copper Chest";
    }

    @Override
    protected boolean canPairWith(Block other) {
        return other.getId() == this.getId();
    }
}
