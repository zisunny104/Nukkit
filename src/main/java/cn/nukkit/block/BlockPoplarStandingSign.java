package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;

public class BlockPoplarStandingSign extends BlockSignPost {

    public BlockPoplarStandingSign() {
        this(0);
    }

    public BlockPoplarStandingSign(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Sign Post";
    }

    @Override
    public int getId() {
        return POPLAR_STANDING_SIGN;
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
