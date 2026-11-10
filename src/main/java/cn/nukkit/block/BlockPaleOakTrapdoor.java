package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPaleOakTrapdoor extends BlockTrapdoor {

    public BlockPaleOakTrapdoor() {
        this(0);
    }

    public BlockPaleOakTrapdoor(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pale Oak Trapdoor";
    }

    @Override
    public int getId() {
        return PALE_OAK_TRAPDOOR;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
