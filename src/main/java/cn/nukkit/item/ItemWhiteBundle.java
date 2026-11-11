package cn.nukkit.item;

public class ItemWhiteBundle extends Item {

    public ItemWhiteBundle() {
        this(0, 1);
    }

    public ItemWhiteBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemWhiteBundle(Integer meta, int count) {
        super(WHITE_BUNDLE, meta, count, "White Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
