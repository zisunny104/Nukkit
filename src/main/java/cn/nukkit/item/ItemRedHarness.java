package cn.nukkit.item;

public class ItemRedHarness extends Item {

    public ItemRedHarness() {
        this(0, 1);
    }

    public ItemRedHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemRedHarness(Integer meta, int count) {
        super(RED_HARNESS, meta, count, "Red Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
