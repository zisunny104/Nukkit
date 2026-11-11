package cn.nukkit.item;

public class ItemSkullPotterySherd extends Item {

    public ItemSkullPotterySherd() {
        this(0, 1);
    }

    public ItemSkullPotterySherd(Integer meta) {
        this(meta, 1);
    }

    public ItemSkullPotterySherd(Integer meta, int count) {
        super(SKULL_POTTERY_SHERD, meta, count, "Skull Pottery Sherd");
    }
}
