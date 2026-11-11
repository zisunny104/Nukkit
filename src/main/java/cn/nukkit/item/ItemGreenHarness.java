package cn.nukkit.item;

public class ItemGreenHarness extends Item {

    public ItemGreenHarness() {
        this(0, 1);
    }

    public ItemGreenHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemGreenHarness(Integer meta, int count) {
        super(GREEN_HARNESS, meta, count, "Green Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
