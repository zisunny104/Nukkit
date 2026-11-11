package cn.nukkit.item;

public class ItemMagentaCushion extends Item {

    public ItemMagentaCushion() {
        this(0, 1);
    }

    public ItemMagentaCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemMagentaCushion(Integer meta, int count) {
        super(MAGENTA_CUSHION, meta, count, "Magenta Cushion");
    }
}
