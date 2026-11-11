package cn.nukkit.item;

public class ItemLimeBundle extends Item {

    public ItemLimeBundle() {
        this(0, 1);
    }

    public ItemLimeBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemLimeBundle(Integer meta, int count) {
        super(LIME_BUNDLE, meta, count, "Lime Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
