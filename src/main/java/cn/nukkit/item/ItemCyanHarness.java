package cn.nukkit.item;

public class ItemCyanHarness extends Item {

    public ItemCyanHarness() {
        this(0, 1);
    }

    public ItemCyanHarness(Integer meta) {
        this(meta, 1);
    }

    public ItemCyanHarness(Integer meta, int count) {
        super(CYAN_HARNESS, meta, count, "Cyan Harness");
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
