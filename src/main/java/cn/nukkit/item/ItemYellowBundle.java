package cn.nukkit.item;

public class ItemYellowBundle extends Item {

    public ItemYellowBundle() {
        this(0, 1);
    }

    public ItemYellowBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemYellowBundle(Integer meta, int count) {
        super(YELLOW_BUNDLE, meta, count, "Yellow Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
