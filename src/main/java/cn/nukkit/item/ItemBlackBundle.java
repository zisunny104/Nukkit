package cn.nukkit.item;

public class ItemBlackBundle extends Item {

    public ItemBlackBundle() {
        this(0, 1);
    }

    public ItemBlackBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemBlackBundle(Integer meta, int count) {
        super(BLACK_BUNDLE, meta, count, "Black Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
