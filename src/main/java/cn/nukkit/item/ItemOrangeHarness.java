package cn.nukkit.item;

public class ItemOrangeHarness extends Item {

    public ItemOrangeHarness() {
        this(0, 1);
    }

    public ItemOrangeHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemOrangeHarness(Integer meta, int count) {
        super(ORANGE_HARNESS, meta, count, "Orange Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
