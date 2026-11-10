package cn.nukkit.block;

public class BlockPaleOakShelf extends BlockTransparentMeta {

    public BlockPaleOakShelf() {
        this(0);
    }

    public BlockPaleOakShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return PALE_OAK_SHELF;
    }

    @Override
    public String getName() {
        return "Pale Oak Shelf";
    }
}
