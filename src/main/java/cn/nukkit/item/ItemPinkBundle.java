package cn.nukkit.item;

public class ItemPinkBundle extends Item {

    public ItemPinkBundle() {
        this(0, 1);
    }

    public ItemPinkBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemPinkBundle(Integer meta, int count) {
        super(PINK_BUNDLE, meta, count, "Pink Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
