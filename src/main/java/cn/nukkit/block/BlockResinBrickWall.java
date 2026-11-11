package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.utils.BlockColor;

public class BlockResinBrickWall extends BlockWallIndependentID {

    public BlockResinBrickWall() {
        this(0);
    }

    public BlockResinBrickWall(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Resin Brick Wall";
    }

    @Override
    public int getId() {
        return RESIN_BRICK_WALL;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe()) {
            return new Item[]{
                    toItem()
            };
        } else {
            return new Item[0];
        }
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.LIGHT_GRAY_TERRACOTA_BLOCK_COLOR;
    }
}
