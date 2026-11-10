package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.utils.BlockColor;

public class BlockPaleOakFence extends BlockFence {

    public BlockPaleOakFence() {
        this(0);
    }

    public BlockPaleOakFence(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pale Oak Fence";
    }

    @Override
    public int getId() {
        return PALE_OAK_FENCE;
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
