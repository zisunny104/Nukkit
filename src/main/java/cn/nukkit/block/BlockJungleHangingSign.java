package cn.nukkit.block;

public class BlockJungleHangingSign extends BlockHangingSign {

    public BlockJungleHangingSign() {
        this(0);
    }

    public BlockJungleHangingSign(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return JUNGLE_HANGING_SIGN;
    }

    @Override
    public String getName() {
        return "Jungle Hanging Sign";
    }
}
