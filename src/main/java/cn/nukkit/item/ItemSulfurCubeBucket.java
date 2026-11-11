package cn.nukkit.item;

public class ItemSulfurCubeBucket extends Item {

    public ItemSulfurCubeBucket() {
        this(0, 1);
    }

    public ItemSulfurCubeBucket(Integer meta) {
        this(meta, 1);
    }

    public ItemSulfurCubeBucket(Integer meta, int count) {
        super(SULFUR_CUBE_BUCKET, meta, count, "Sulfur Cube Bucket");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
