package cn.nukkit.block;

public class BlockWarpedHangingSign extends BlockHangingSign {

    public BlockWarpedHangingSign() {
        this(0);
    }

    public BlockWarpedHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WARPED_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Warped Hanging Sign";
    }
}
