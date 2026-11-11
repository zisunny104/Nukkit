package cn.nukkit.block;

public class BlockBirchHangingSign extends BlockHangingSign {

    public BlockBirchHangingSign() {
        this(0);
    }

    public BlockBirchHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BIRCH_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Birch Hanging Sign";
    }
}
