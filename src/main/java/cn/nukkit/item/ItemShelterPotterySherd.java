package cn.nukkit.item;

public class ItemShelterPotterySherd extends Item {

    public ItemShelterPotterySherd() {
        this(0, 1);
    }

    public ItemShelterPotterySherd(Integer meta) {
        this(meta, 1);
    }

    public ItemShelterPotterySherd(Integer meta, int count) {
        super(SHELTER_POTTERY_SHERD, meta, count, "Shelter Pottery Sherd");
    }
}
