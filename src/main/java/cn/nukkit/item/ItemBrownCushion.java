package cn.nukkit.item;

public class ItemBrownCushion extends Item {

    public ItemBrownCushion() {
        this(0, 1);
    }

    public ItemBrownCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemBrownCushion(Integer meta, int count) {
        super(BROWN_CUSHION, meta, count, "Brown Cushion");
    }
}
