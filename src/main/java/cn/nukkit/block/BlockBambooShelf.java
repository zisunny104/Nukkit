package cn.nukkit.block;

public class BlockBambooShelf extends BlockShelf {

    public BlockBambooShelf() {
        this(0);
    }

    public BlockBambooShelf(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BAMBOO_SHELF;
    }

    @Override
    public String getName() {
        return "Bamboo Shelf";
    }
}
