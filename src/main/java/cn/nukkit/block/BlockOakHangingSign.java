package cn.nukkit.block;

public class BlockOakHangingSign extends BlockHangingSign {

    public BlockOakHangingSign() {
        this(0);
    }

    public BlockOakHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return OAK_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Oak Hanging Sign";
    }
}
