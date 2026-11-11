package cn.nukkit.item;

public class ItemPurpleHarness extends Item {

    public ItemPurpleHarness() {
        this(0, 1);
    }

    public ItemPurpleHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemPurpleHarness(Integer meta, int count) {
        super(PURPLE_HARNESS, meta, count, "Purple Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
