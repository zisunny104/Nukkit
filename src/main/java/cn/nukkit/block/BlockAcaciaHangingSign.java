package cn.nukkit.block;

public class BlockAcaciaHangingSign extends BlockHangingSign {

    public BlockAcaciaHangingSign() {
        this(0);
    }

    public BlockAcaciaHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return ACACIA_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Acacia Hanging Sign";
    }
}
