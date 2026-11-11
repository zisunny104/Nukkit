package cn.nukkit.item;

public class ItemPinkHarness extends Item {

    public ItemPinkHarness() {
        this(0, 1);
    }

    public ItemPinkHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemPinkHarness(Integer meta, int count) {
        super(PINK_HARNESS, meta, count, "Pink Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
