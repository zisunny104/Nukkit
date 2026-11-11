package cn.nukkit.item;

public class ItemPurpleBundle extends Item {

    public ItemPurpleBundle() {
        this(0, 1);
    }

    public ItemPurpleBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemPurpleBundle(Integer meta, int count) {
        super(PURPLE_BUNDLE, meta, count, "Purple Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
