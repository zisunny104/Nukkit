package cn.nukkit.item;

public class ItemGreenCushion extends Item {

    public ItemGreenCushion() {
        this(0, 1);
    }

    public ItemGreenCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemGreenCushion(Integer meta, int count) {
        super(GREEN_CUSHION, meta, count, "Green Cushion");
    }
}
