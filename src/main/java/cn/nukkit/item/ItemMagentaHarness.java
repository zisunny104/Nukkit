package cn.nukkit.item;

public class ItemMagentaHarness extends Item {

    public ItemMagentaHarness() {
        this(0, 1);
    }

    public ItemMagentaHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemMagentaHarness(Integer meta, int count) {
        super(MAGENTA_HARNESS, meta, count, "Magenta Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
