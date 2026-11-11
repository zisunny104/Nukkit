package cn.nukkit.item;

public class ItemRecordTears extends ItemRecord {

    public ItemRecordTears() {
        this(0, 1);
    }

    public ItemRecordTears(Integer meta) {
        this(meta, 1);
    }

    public ItemRecordTears(Integer meta, int count) {
        super(RECORD_TEARS, meta, count);
    }

    @Override
    public String getSoundId() {
        return "record.tears";
    }

    @Override
    public String getDiscName() {
        return "Tears";
    }
}
