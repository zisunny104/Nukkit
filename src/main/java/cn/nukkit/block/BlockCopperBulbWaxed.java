package cn.nukkit.block;

public class BlockCopperBulbWaxed extends BlockCopperBulb {

    public BlockCopperBulbWaxed() {
        this(0);
    }

    public BlockCopperBulbWaxed(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Waxed Copper Bulb";
    }

    @Override
    public int getId() {
        return WAXED_COPPER_BULB;
    }

    @Override
    public boolean isWaxed() {
        return true;
    }
}
