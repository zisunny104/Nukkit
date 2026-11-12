package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.utils.BlockColor;

/**
 * Sent by the client in block pick requests; never exists in a world.
 */
public class BlockClientRequestPlaceholder extends BlockSolid {

    @Override
    public String getName() {
        return "Client Request Placeholder Block";
    }

    @Override
    public int getId() {
        return 720;
    }

    @Override
    public double getHardness() {
        return -1;
    }

    @Override
    public double getResistance() {
        return 18000000;
    }

    @Override
    public boolean isBreakable(Item item) {
        return false;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.TRANSPARENT_BLOCK_COLOR;
    }
}
