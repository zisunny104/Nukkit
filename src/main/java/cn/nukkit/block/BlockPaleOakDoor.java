package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.utils.BlockColor;

public class BlockPaleOakDoor extends BlockDoorWood {

    public BlockPaleOakDoor() {
        this(0);
    }

    public BlockPaleOakDoor(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pale Oak Door Block";
    }

    @Override
    public int getId() {
        return PALE_OAK_DOOR_BLOCK;
    }

    @Override
    public Item toItem() {
        return Item.get(Item.PALE_OAK_DOOR);
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
