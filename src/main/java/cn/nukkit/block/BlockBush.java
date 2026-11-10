package cn.nukkit.block;

public class BlockBush extends BlockFlowable {

    public BlockBush() {
        this(0);
    }

    public BlockBush(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BUSH_BLOCK;
    }

    @Override
    public String getName() {
        return "Bush";
    }
}
