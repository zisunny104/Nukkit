package cn.nukkit.item;

public class ItemFlowPotterySherd extends Item {

    public ItemFlowPotterySherd() {
        this(0, 1);
    }

    public ItemFlowPotterySherd(Integer meta) {
        this(meta, 1);
    }

    public ItemFlowPotterySherd(Integer meta, int count) {
        super(FLOW_POTTERY_SHERD, meta, count, "Flow Pottery Sherd");
    }
}
