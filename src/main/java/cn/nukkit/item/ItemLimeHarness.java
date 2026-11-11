package cn.nukkit.item;

public class ItemLimeHarness extends Item {

    public ItemLimeHarness() {
        this(0, 1);
    }

    public ItemLimeHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemLimeHarness(Integer meta, int count) {
        super(LIME_HARNESS, meta, count, "Lime Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
