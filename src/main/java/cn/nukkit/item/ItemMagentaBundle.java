package cn.nukkit.item;

public class ItemMagentaBundle extends Item {

    public ItemMagentaBundle() {
        this(0, 1);
    }

    public ItemMagentaBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemMagentaBundle(Integer meta, int count) {
        super(MAGENTA_BUNDLE, meta, count, "Magenta Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
