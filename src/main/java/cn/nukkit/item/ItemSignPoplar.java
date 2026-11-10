package cn.nukkit.item;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;

public class ItemSignPoplar extends Item {

    public ItemSignPoplar() {
        this(0, 1);
    }

    public ItemSignPoplar(Integer meta) {
        this(meta, 1);
    }

    public ItemSignPoplar(Integer meta, int count) {
        super(POPLAR_SIGN, 0, count, "Poplar Sign");
        this.block = Block.get(BlockID.POPLAR_STANDING_SIGN);
    }

    @Override
    public int getMaxStackSize() {
        return 16;
    }
}
