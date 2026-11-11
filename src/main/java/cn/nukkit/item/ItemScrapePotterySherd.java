package cn.nukkit.item;

public class ItemScrapePotterySherd extends Item {

    public ItemScrapePotterySherd() {
        this(0, 1);
    }

    public ItemScrapePotterySherd(Integer meta) {
        this(meta, 1);
    }

    public ItemScrapePotterySherd(Integer meta, int count) {
        super(SCRAPE_POTTERY_SHERD, meta, count, "Scrape Pottery Sherd");
    }
}
