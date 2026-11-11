package cn.nukkit.item;

public class ItemLightGrayBundle extends Item {

    public ItemLightGrayBundle() {
        this(0, 1);
    }

    public ItemLightGrayBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemLightGrayBundle(Integer meta, int count) {
        super(LIGHT_GRAY_BUNDLE, meta, count, "Light Gray Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
