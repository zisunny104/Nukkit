package cn.nukkit.item;

public class ItemDangerPotterySherd extends Item {

    public ItemDangerPotterySherd() {
        this(0, 1);
    }

    public ItemDangerPotterySherd(Integer meta) {
        this(meta, 1);
    }

    public ItemDangerPotterySherd(Integer meta, int count) {
        super(DANGER_POTTERY_SHERD, meta, count, "Danger Pottery Sherd");
    }
}
