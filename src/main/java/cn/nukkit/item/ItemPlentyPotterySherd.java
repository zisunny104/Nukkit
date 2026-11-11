package cn.nukkit.item;

public class ItemPlentyPotterySherd extends Item {

    public ItemPlentyPotterySherd() {
        this(0, 1);
    }

    public ItemPlentyPotterySherd(Integer meta) {
        this(meta, 1);
    }

    public ItemPlentyPotterySherd(Integer meta, int count) {
        super(PLENTY_POTTERY_SHERD, meta, count, "Plenty Pottery Sherd");
    }
}
