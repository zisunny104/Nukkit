package cn.nukkit.item;

public class ItemYellowHarness extends Item {

    public ItemYellowHarness() {
        this(0, 1);
    }

    public ItemYellowHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemYellowHarness(Integer meta, int count) {
        super(YELLOW_HARNESS, meta, count, "Yellow Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
