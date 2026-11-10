package cn.nukkit.block;

public class BlockAcaciaShelf extends BlockTransparentMeta {

    public BlockAcaciaShelf() {
        this(0);
    }

    public BlockAcaciaShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return ACACIA_SHELF;
    }

    @Override
    public String getName() {
        return "Acacia Shelf";
    }
}
