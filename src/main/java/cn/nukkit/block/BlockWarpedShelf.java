package cn.nukkit.block;

public class BlockWarpedShelf extends BlockTransparentMeta {

    public BlockWarpedShelf() {
        this(0);
    }

    public BlockWarpedShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WARPED_SHELF;
    }

    @Override
    public String getName() {
        return "Warped Shelf";
    }
}
