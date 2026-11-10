package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPaleOakPressurePlate extends BlockPressurePlateWood {

    public BlockPaleOakPressurePlate() {
        this(0);
    }

    public BlockPaleOakPressurePlate(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pale Oak Pressure Plate";
    }

    @Override
    public int getId() {
        return PALE_OAK_PRESSURE_PLATE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
