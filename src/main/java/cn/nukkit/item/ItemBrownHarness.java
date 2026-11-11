package cn.nukkit.item;

public class ItemBrownHarness extends Item {

    public ItemBrownHarness() {
        this(0, 1);
    }

    public ItemBrownHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemBrownHarness(Integer meta, int count) {
        super(BROWN_HARNESS, meta, count, "Brown Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
