package cn.nukkit.item;

public class ItemBlueHarness extends Item {

    public ItemBlueHarness() {
        this(0, 1);
    }

    public ItemBlueHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemBlueHarness(Integer meta, int count) {
        super(BLUE_HARNESS, meta, count, "Blue Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
