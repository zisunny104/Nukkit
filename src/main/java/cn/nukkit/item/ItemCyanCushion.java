package cn.nukkit.item;

public class ItemCyanCushion extends Item {

    public ItemCyanCushion() {
        this(0, 1);
    }

    public ItemCyanCushion(Integer meta) {
        this(meta, 1);
    }

    public ItemCyanCushion(Integer meta, int count) {
        super(CYAN_CUSHION, meta, count, "Cyan Cushion");
    }
}
