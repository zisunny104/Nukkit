package cn.nukkit.item;

public class ItemLightBlueCushion extends Item {

    public ItemLightBlueCushion() {
        this(0, 1);
    }

    public ItemLightBlueCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemLightBlueCushion(Integer meta, int count) {
        super(LIGHT_BLUE_CUSHION, meta, count, "Light Blue Cushion");
    }
}
