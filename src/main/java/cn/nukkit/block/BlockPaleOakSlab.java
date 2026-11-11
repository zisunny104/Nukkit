package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.utils.BlockColor;

public class BlockPaleOakSlab extends BlockSlabWood {

    public BlockPaleOakSlab() {
        this(0);
    }

    public BlockPaleOakSlab(int meta) {
        super(meta, PALE_OAK_DOUBLE_SLAB);
    }

    @Override
    public String getName() {
        return "Pale Oak Slab";
    }

    @Override
    public int getId() {
        return PALE_OAK_SLAB;
    }

    @Override
    public Item[] getDrops(Item item) {
        return new Item[]{
                this.toItem()
        };
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    @Override
    public boolean hasTopBit() {
        return (this.getDamage() & 0x01) == 1;
    }

    @Override
    public void setTopBit(boolean topBit) {
        this.setDamage(topBit ? 1 : 0);
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WHITE_TERRACOTA_BLOCK_COLOR;
    }
}
