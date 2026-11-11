package cn.nukkit.item;

public class ItemPurpleCushion extends Item {

    public ItemPurpleCushion() {
        this(0, 1);
    }

    public ItemPurpleCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemPurpleCushion(Integer meta, int count) {
        super(PURPLE_CUSHION, meta, count, "Purple Cushion");
    }
}
