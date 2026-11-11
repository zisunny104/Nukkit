package cn.nukkit.item;

public class ItemGusterBannerPattern extends Item {

    public ItemGusterBannerPattern() {
        this(0, 1);
    }

    public ItemGusterBannerPattern(Integer meta) {
        this(meta, 1);
    }

    public ItemGusterBannerPattern(Integer meta, int count) {
        super(GUSTER_BANNER_PATTERN, meta, count, "Guster Banner Pattern");
    }
}
