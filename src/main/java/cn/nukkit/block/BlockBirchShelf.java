package cn.nukkit.block;

public class BlockBirchShelf extends BlockTransparentMeta {

    public BlockBirchShelf() {
        this(0);
    }

    public BlockBirchShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BIRCH_SHELF;
    }

    @Override
    public String getName() {
        return "Birch Shelf";
    }
}
