package cn.nukkit.item;

public class ItemGreenBundle extends Item {

    public ItemGreenBundle() {
        this(0, 1);
    }

    public ItemGreenBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemGreenBundle(Integer meta, int count) {
        super(GREEN_BUNDLE, meta, count, "Green Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
