package cn.nukkit.block;

public class BlockDarkOakShelf extends BlockTransparentMeta {

    public BlockDarkOakShelf() {
        this(0);
    }

    public BlockDarkOakShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DARK_OAK_SHELF;
    }

    @Override
    public String getName() {
        return "Dark Oak Shelf";
    }
}
