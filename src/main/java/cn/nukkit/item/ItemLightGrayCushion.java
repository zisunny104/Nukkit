package cn.nukkit.item;

public class ItemLightGrayCushion extends Item {

    public ItemLightGrayCushion() {
        this(0, 1);
    }

    public ItemLightGrayCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemLightGrayCushion(Integer meta, int count) {
        super(LIGHT_GRAY_CUSHION, meta, count, "Light Gray Cushion");
    }
}
