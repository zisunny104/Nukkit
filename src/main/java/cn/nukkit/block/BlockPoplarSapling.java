package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;

public class BlockPoplarSapling extends BlockSapling {

    public BlockPoplarSapling() {
        this(0);
    }

    public BlockPoplarSapling(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return POPLAR_SAPLING;
    }

    @Override
    public String getName() {
        return "Poplar Sapling";
    }

    @Override
    protected boolean growTreeHere() {
        // TODO: tree generator for this sapling, it does not grow until it exists
        return false;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }
}
