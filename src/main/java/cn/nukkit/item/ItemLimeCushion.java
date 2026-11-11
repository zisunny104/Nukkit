package cn.nukkit.item;

public class ItemLimeCushion extends Item {

    public ItemLimeCushion() {
        this(0, 1);
    }

    public ItemLimeCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemLimeCushion(Integer meta, int count) {
        super(LIME_CUSHION, meta, count, "Lime Cushion");
    }
}
