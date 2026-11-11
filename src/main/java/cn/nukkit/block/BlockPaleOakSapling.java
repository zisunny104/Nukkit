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
        // pale oak only grows as a 2x2 group, official trunk, canopy and branches (TODO: creaking heart, resin)
        cn.nukkit.math.Vector2 v = this.findSaplings(this.getDamage() & 0x7);
        if (v == null) {
            return false;
        }
        return growGenerated(new cn.nukkit.level.generator.object.tree.ObjectPaleOakTree(),
                this.add(v.getFloorX(), 0, v.getFloorY()), true);
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }
}
