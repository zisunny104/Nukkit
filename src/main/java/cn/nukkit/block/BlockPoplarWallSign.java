package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;

public class BlockPoplarWallSign extends BlockWallSign {

    public BlockPoplarWallSign() {
        this(0);
    }

    public BlockPoplarWallSign(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Wall Sign";
    }

    @Override
    public int getId() {
        return POPLAR_WALL_SIGN;
    }

    @Override
    public Item toItem() {
        return Item.get(ItemID.POPLAR_SIGN);
    }

    @Override
    protected int getPostId() {
        return POPLAR_STANDING_SIGN;
    }

    @Override
    protected int getWallId() {
        return POPLAR_WALL_SIGN;
    }
}
