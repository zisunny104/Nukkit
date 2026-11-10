package cn.nukkit.item;

import cn.nukkit.block.Block;

public class ItemDoorPaleOak extends Item {

    public ItemDoorPaleOak() {
        this(0, 1);
    }

    public ItemDoorPaleOak(Integer meta) {
        this(meta, 1);
    }

    public ItemDoorPaleOak(Integer meta, int count) {
        super(PALE_OAK_DOOR, 0, count, "Pale Oak Door");
        this.block = Block.get(PALE_OAK_DOOR_BLOCK);
    }
}
