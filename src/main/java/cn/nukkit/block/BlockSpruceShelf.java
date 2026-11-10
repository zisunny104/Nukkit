package cn.nukkit.block;

public class BlockSpruceShelf extends BlockTransparentMeta {

    public BlockSpruceShelf() {
        this(0);
    }

    public BlockSpruceShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return SPRUCE_SHELF;
    }

    @Override
    public String getName() {
        return "Spruce Shelf";
    }
}
