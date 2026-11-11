package cn.nukkit.block;

public class BlockOakShelf extends BlockTransparentMeta {

    public BlockOakShelf() {
        this(0);
    }

    public BlockOakShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return OAK_SHELF;
    }

    @Override
    public String getName() {
        return "Oak Shelf";
    }
}
