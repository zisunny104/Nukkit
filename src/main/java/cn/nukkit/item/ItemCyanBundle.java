package cn.nukkit.item;

public class ItemCyanBundle extends Item {

    public ItemCyanBundle() {
        this(0, 1);
    }

    public ItemCyanBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemCyanBundle(Integer meta, int count) {
        super(CYAN_BUNDLE, meta, count, "Cyan Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
