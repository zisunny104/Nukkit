package cn.nukkit.item;

public class ItemBlackCushion extends Item {

    public ItemBlackCushion() {
        this(0, 1);
    }

    public ItemBlackCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemBlackCushion(Integer meta, int count) {
        super(BLACK_CUSHION, meta, count, "Black Cushion");
    }
}
