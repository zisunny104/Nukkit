package cn.nukkit.item;

public class ItemBlueBundle extends Item {

    public ItemBlueBundle() {
        this(0, 1);
    }

    public ItemBlueBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemBlueBundle(Integer meta, int count) {
        super(BLUE_BUNDLE, meta, count, "Blue Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
