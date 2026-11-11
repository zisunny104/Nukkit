package cn.nukkit.item;

public class ItemGrayHarness extends Item {

    public ItemGrayHarness() {
        this(0, 1);
    }

    public ItemGrayHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemGrayHarness(Integer meta, int count) {
        super(GRAY_HARNESS, meta, count, "Gray Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
