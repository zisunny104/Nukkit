package cn.nukkit.item;

public class ItemBlueCushion extends Item {

    public ItemBlueCushion() {
        this(0, 1);
    }

    public ItemBlueCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemBlueCushion(Integer meta, int count) {
        super(BLUE_CUSHION, meta, count, "Blue Cushion");
    }
}
