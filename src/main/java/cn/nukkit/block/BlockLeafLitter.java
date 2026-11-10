package cn.nukkit.block;

public class BlockLeafLitter extends BlockPinkPetals {

    public BlockLeafLitter() {
        this(0);
    }

    public BlockLeafLitter(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return LEAF_LITTER;
    }

    @Override
    public String getName() {
        return "Leaf Litter";
    }

    @Override
    protected boolean canStandOn(Block down) {
        return down.isSolid() && !down.isTransparent();
    }
}
