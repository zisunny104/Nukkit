package cn.nukkit.block;

public class BlockCherryHangingSign extends BlockHangingSign {

    public BlockCherryHangingSign() {
        this(0);
    }

    public BlockCherryHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CHERRY_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Cherry Hanging Sign";
    }
}
