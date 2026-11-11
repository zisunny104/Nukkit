package cn.nukkit.item;

public class ItemGusterPotterySherd extends Item {

    public ItemGusterPotterySherd() {
        this(0, 1);
    }

    public ItemGusterPotterySherd(Integer meta) {
        this(meta, 1);
    }

    public ItemGusterPotterySherd(Integer meta, int count) {
        super(GUSTER_POTTERY_SHERD, meta, count, "Guster Pottery Sherd");
    }
}
