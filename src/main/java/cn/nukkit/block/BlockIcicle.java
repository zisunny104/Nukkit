package cn.nukkit.block;

public class BlockIcicle extends BlockTransparentMeta {

    public BlockIcicle() {
        this(0);
    }

    public BlockIcicle(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return ICICLE;
    }

    @Override
    public String getName() {
        return "Icicle";
    }
}
