package cn.nukkit.item;

public class ItemOrangeCushion extends Item {

    public ItemOrangeCushion() {
        this(0, 1);
    }

    public ItemOrangeCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemOrangeCushion(Integer meta, int count) {
        super(ORANGE_CUSHION, meta, count, "Orange Cushion");
    }
}
