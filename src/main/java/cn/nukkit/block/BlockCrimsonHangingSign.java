package cn.nukkit.block;

public class BlockCrimsonHangingSign extends BlockHangingSign {

    public BlockCrimsonHangingSign() {
        this(0);
    }

    public BlockCrimsonHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CRIMSON_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Crimson Hanging Sign";
    }
}
