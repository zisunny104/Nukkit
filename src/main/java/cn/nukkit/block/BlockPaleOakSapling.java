package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;

public class BlockPaleOakSapling extends BlockSapling {

    public BlockPaleOakSapling() {
        this(0);
    }

    public BlockPaleOakSapling(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return PALE_OAK_SAPLING;
    }

    @Override
    public String getName() {
        return "Pale Oak Sapling";
    }

    @Override
    protected boolean growTreeHere() {
        // TODO: pale oak tree generator, the sapling does not grow until it exists
        return false;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }
}
