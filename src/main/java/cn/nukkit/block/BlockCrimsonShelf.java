package cn.nukkit.block;

public class BlockCrimsonShelf extends BlockTransparentMeta {

    public BlockCrimsonShelf() {
        this(0);
    }

    public BlockCrimsonShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CRIMSON_SHELF;
    }

    @Override
    public String getName() {
        return "Crimson Shelf";
    }
}
