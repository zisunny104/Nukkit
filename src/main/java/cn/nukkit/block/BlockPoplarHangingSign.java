package cn.nukkit.block;

public class BlockPoplarHangingSign extends BlockTransparentMeta {

    public BlockPoplarHangingSign() {
        this(0);
    }

    public BlockPoplarHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return POPLAR_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Poplar Hanging Sign";
    }
}
