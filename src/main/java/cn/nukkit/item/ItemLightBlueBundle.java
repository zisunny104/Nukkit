package cn.nukkit.item;

public class ItemLightBlueBundle extends Item {

    public ItemLightBlueBundle() {
        this(0, 1);
    }

    public ItemLightBlueBundle(Integer meta) {
        this(meta, 1);
    }

    public ItemLightBlueBundle(Integer meta, int count) {
        super(LIGHT_BLUE_BUNDLE, meta, count, "Light Blue Bundle");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
