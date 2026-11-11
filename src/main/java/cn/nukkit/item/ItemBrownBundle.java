package cn.nukkit.item;

public class ItemBrownBundle extends Item {

    public ItemBrownBundle() {
        this(0, 1);
    }

    public ItemBrownBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemBrownBundle(Integer meta, int count) {
        super(BROWN_BUNDLE, meta, count, "Brown Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
