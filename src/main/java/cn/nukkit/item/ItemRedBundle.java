package cn.nukkit.item;

public class ItemRedBundle extends Item {

    public ItemRedBundle() {
        this(0, 1);
    }

    public ItemRedBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemRedBundle(Integer meta, int count) {
        super(RED_BUNDLE, meta, count, "Red Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
