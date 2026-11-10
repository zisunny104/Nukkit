package cn.nukkit.item;

import cn.nukkit.block.Block;

public class ItemDoorPoplar extends Item {

    public ItemDoorPoplar() {
        this(0, 1);
    }

    public ItemDoorPoplar(Integer meta) {
        this(meta, 1);
    }

    public ItemDoorPoplar(Integer meta, int count) {
        super(POPLAR_DOOR, 0, count, "Poplar Door");
        this.block = Block.get(POPLAR_DOOR_BLOCK);
    }
}
