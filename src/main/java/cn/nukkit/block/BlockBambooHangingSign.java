package cn.nukkit.block;

public class BlockBambooHangingSign extends BlockHangingSign {

    public BlockBambooHangingSign() {
        this(0);
    }

    public BlockBambooHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BAMBOO_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Bamboo Hanging Sign";
    }
}
