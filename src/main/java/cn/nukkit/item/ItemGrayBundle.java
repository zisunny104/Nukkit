package cn.nukkit.item;

public class ItemGrayBundle extends Item {

    public ItemGrayBundle() {
        this(0, 1);
    }

    public ItemGrayBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemGrayBundle(Integer meta, int count) {
        super(GRAY_BUNDLE, meta, count, "Gray Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
