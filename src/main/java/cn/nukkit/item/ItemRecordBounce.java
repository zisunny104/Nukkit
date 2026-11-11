package cn.nukkit.item;

public class ItemRecordBounce extends ItemRecord {

    public ItemRecordBounce() {
        this(0, 1);
    }

    public ItemRecordBounce(Integer meta) {
        this(meta, 1);
    }

    public ItemRecordBounce(Integer meta, int count) {
        super(RECORD_BOUNCE, meta, count);
    }

    @Override
    public String getSoundId() {
        return "record.bounce";
    }

    @Override
    public String getDiscName() {
        return "Bounce";
    }
}
