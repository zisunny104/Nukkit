package cn.nukkit.block;

public class BlockCopperBulbWeatheredWaxed extends BlockCopperBulbWeathered {

    public BlockCopperBulbWeatheredWaxed() {
        this(0);
    }

    public BlockCopperBulbWeatheredWaxed(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Waxed Weathered Copper Bulb";
    }

    @Override
    public int getId() {
        return WAXED_WEATHERED_COPPER_BULB;
    }

    @Override
    public boolean isWaxed() {
        return true;
    }
}
