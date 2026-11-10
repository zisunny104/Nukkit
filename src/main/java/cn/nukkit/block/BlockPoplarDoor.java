package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.utils.BlockColor;

public class BlockPoplarDoor extends BlockDoorWood {

    public BlockPoplarDoor() {
        this(0);
    }

    public BlockPoplarDoor(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Door Block";
    }

    @Override
    public int getId() {
        return POPLAR_DOOR_BLOCK;
    }

    @Override
    public Item toItem() {
        return Item.get(Item.POPLAR_DOOR);
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
