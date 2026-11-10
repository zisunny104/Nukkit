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
        cn.nukkit.level.generator.object.tree.ObjectPoplarTree tree = new cn.nukkit.level.generator.object.tree.ObjectPoplarTree();
        cn.nukkit.level.ListChunkManager chunkManager = new cn.nukkit.level.ListChunkManager(this.level);
        tree.placeObject(chunkManager, (int) this.x, (int) this.y, (int) this.z, new cn.nukkit.math.NukkitRandom());
        cn.nukkit.event.level.StructureGrowEvent ev = new cn.nukkit.event.level.StructureGrowEvent(this, chunkManager.getBlocks());
        this.level.getServer().getPluginManager().callEvent(ev);
        if (ev.isCancelled()) {
            return false;
        }
        for (Block block : ev.getBlockList()) {
            this.level.setBlock(block, block);
        }
        return true;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }
}
