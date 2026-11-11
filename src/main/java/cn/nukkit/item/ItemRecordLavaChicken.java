package cn.nukkit.item;

public class ItemRecordLavaChicken extends ItemRecord {

    public ItemRecordLavaChicken() {
        this(0, 1);
    }

    public ItemRecordLavaChicken(Integer meta) {
        this(meta, 1);
    }

    public ItemRecordLavaChicken(Integer meta, int count) {
        super(RECORD_LAVA_CHICKEN, meta, count);
    }

    @Override
    public String getSoundId() {
        return "record.lava_chicken";
    }

    @Override
    public String getDiscName() {
        return "Lava Chicken";
    }
}
