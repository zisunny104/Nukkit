package cn.nukkit.item;

public class ItemLightGrayHarness extends Item {

    public ItemLightGrayHarness() {
        this(0, 1);
    }

    public ItemLightGrayHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemLightGrayHarness(Integer meta, int count) {
        super(LIGHT_GRAY_HARNESS, meta, count, "Light Gray Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
