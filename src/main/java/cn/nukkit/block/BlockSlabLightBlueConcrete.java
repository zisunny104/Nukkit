package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.utils.BlockColor;

public class BlockSlabLightBlueConcrete extends BlockSlab {

    public BlockSlabLightBlueConcrete() {
        this(0);
    }

    public BlockSlabLightBlueConcrete(int meta) {
        super(meta, LIGHT_BLUE_CONCRETE_DOUBLE_SLAB);
    }

    @Override
    public int getId() {
        return LIGHT_BLUE_CONCRETE_SLAB;
    }

    @Override
    public String getSlabName() {
        return "Light Blue Concrete Slab";
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
