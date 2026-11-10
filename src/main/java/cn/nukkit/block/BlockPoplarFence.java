package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.utils.BlockColor;

public class BlockPoplarFence extends BlockFence {

    public BlockPoplarFence() {
        this(0);
    }

    public BlockPoplarFence(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Fence";
    }

    @Override
    public int getId() {
        return POPLAR_FENCE;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
