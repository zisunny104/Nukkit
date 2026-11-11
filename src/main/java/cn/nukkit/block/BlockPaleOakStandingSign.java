package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;

public class BlockPaleOakStandingSign extends BlockSignPost {

    public BlockPaleOakStandingSign() {
        this(0);
    }

    public BlockPaleOakStandingSign(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pale Oak Sign Post";
    }

    @Override
    public int getId() {
        return PALE_OAK_STANDING_SIGN;
    }

    @Override
    public Item toItem() {
        return Item.get(ItemID.PALE_OAK_SIGN);
    }

    @Override
    protected int getPostId() {
        return PALE_OAK_STANDING_SIGN;
    }

    @Override
    protected int getWallId() {
        return PALE_OAK_WALL_SIGN;
    }
}
