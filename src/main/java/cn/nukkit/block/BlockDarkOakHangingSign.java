package cn.nukkit.block;

public class BlockDarkOakHangingSign extends BlockHangingSign {

    public BlockDarkOakHangingSign() {
        this(0);
    }

    public BlockDarkOakHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DARK_OAK_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Dark Oak Hanging Sign";
    }
}
