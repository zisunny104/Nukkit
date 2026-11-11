package cn.nukkit.item;

public class ItemOrangeBundle extends Item {

    public ItemOrangeBundle() {
        this(0, 1);
    }

    public ItemOrangeBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemOrangeBundle(Integer meta, int count) {
        super(ORANGE_BUNDLE, meta, count, "Orange Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
