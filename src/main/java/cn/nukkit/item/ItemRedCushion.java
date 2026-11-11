package cn.nukkit.item;

public class ItemRedCushion extends Item {

    public ItemRedCushion() {
        this(0, 1);
    }

    public ItemRedCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemRedCushion(Integer meta, int count) {
        super(RED_CUSHION, meta, count, "Red Cushion");
    }
}
