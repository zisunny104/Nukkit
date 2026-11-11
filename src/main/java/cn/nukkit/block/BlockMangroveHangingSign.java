package cn.nukkit.block;

public class BlockMangroveHangingSign extends BlockHangingSign {

    public BlockMangroveHangingSign() {
        this(0);
    }

    public BlockMangroveHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return MANGROVE_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Mangrove Hanging Sign";
    }
}
