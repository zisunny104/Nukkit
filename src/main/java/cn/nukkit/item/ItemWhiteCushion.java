package cn.nukkit.item;

public class ItemWhiteCushion extends Item {

    public ItemWhiteCushion() {
        this(0, 1);
    }

    public ItemWhiteCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemWhiteCushion(Integer meta, int count) {
        super(WHITE_CUSHION, meta, count, "White Cushion");
    }
}
