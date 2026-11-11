package cn.nukkit.item;

public class ItemFlowBannerPattern extends Item {

    public ItemFlowBannerPattern() {
        this(0, 1);
    }

    public ItemFlowBannerPattern(Integer meta) {
        this(meta, 1);
    }

    public ItemFlowBannerPattern(Integer meta, int count) {
        super(FLOW_BANNER_PATTERN, meta, count, "Flow Banner Pattern");
    }
}
