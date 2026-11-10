package cn.nukkit.block;

import cn.nukkit.utils.BlockColor;

public class BlockPoplarPressurePlate extends BlockPressurePlateWood {

    public BlockPoplarPressurePlate() {
        this(0);
    }

    public BlockPoplarPressurePlate(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Pressure Plate";
    }

    @Override
    public int getId() {
        return POPLAR_PRESSURE_PLATE;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
