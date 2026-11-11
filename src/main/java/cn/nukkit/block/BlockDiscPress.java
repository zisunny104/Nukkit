package cn.nukkit.block;

public class BlockDiscPress extends BlockTransparentMeta {

    public BlockDiscPress() {
        this(0);
    }

    public BlockDiscPress(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DISC_PRESS;
    }

    @Override
    public String getName() {
        return "Disc Press";
    }
}
