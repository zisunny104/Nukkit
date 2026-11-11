package cn.nukkit.item;

public class ItemLightBlueHarness extends Item {

    public ItemLightBlueHarness() {
        this(0, 1);
    }

    public ItemLightBlueHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemLightBlueHarness(Integer meta, int count) {
        super(LIGHT_BLUE_HARNESS, meta, count, "Light Blue Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
