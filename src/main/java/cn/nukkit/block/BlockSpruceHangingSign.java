package cn.nukkit.block;

public class BlockSpruceHangingSign extends BlockHangingSign {

    public BlockSpruceHangingSign() {
        this(0);
    }

    public BlockSpruceHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return SPRUCE_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Spruce Hanging Sign";
    }
}
