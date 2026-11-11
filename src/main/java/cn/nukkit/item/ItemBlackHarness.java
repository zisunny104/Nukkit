package cn.nukkit.item;

public class ItemBlackHarness extends Item {

    public ItemBlackHarness() {
        this(0, 1);
    }

    public ItemBlackHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemBlackHarness(Integer meta, int count) {
        super(BLACK_HARNESS, meta, count, "Black Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
