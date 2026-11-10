package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;

public class BlockSlabCinnabarBrick extends BlockSlab {

    public BlockSlabCinnabarBrick() {
        this(0);
    }

    public BlockSlabCinnabarBrick(int meta) {
        super(meta, CINNABAR_BRICK_DOUBLE_SLAB);
    }

    @Override
    public int getId() {
        return CINNABAR_BRICK_SLAB;
    }

    @Override
    public String getSlabName() {
        return "Cinnabar Brick Slab";
    }

    @Override
    public double getHardness() {
        return 1.5;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
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
        return BlockColor.GRAY_TERRACOTA_BLOCK_COLOR;
    }
}
