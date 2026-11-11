package cn.nukkit.item;

public class ItemYellowCushion extends Item {

    public ItemYellowCushion() {
        this(0, 1);
    }

    public ItemYellowCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemYellowCushion(Integer meta, int count) {
        super(YELLOW_CUSHION, meta, count, "Yellow Cushion");
    }
}
