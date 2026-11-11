package cn.nukkit.item;

public class ItemPinkCushion extends Item {

    public ItemPinkCushion() {
        this(0, 1);
    }

    public ItemPinkCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemPinkCushion(Integer meta, int count) {
        super(PINK_CUSHION, meta, count, "Pink Cushion");
    }
}
