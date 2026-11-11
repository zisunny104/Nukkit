package cn.nukkit.item;

public class ItemWhiteHarness extends Item {

    public ItemWhiteHarness() {
        this(0, 1);
    }

    public ItemWhiteHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemWhiteHarness(Integer meta, int count) {
        super(WHITE_HARNESS, meta, count, "White Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
