package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPoplarTrapdoor extends BlockTrapdoor {

    public BlockPoplarTrapdoor() {
        this(0);
    }

    public BlockPoplarTrapdoor(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Trapdoor";
    }

    @Override
    public int getId() {
        return POPLAR_TRAPDOOR;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
