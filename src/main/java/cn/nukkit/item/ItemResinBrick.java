package cn.nukkit.item;

public class ItemResinBrick extends Item {

    public ItemResinBrick() {
        this(0, 1);
    }

    public ItemResinBrick(Integer meta) {
        this(meta, 1);
    }

    public ItemResinBrick(Integer meta, int count) {
        super(RESIN_BRICK, meta, count, "Resin Brick");
    }
}
