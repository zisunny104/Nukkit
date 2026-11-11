package cn.nukkit.item;

public class ItemGrayCushion extends Item {

    public ItemGrayCushion() {
        this(0, 1);
    }

    public ItemGrayCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemGrayCushion(Integer meta, int count) {
        super(GRAY_CUSHION, meta, count, "Gray Cushion");
    }
}
