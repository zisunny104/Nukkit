package cn.nukkit.block;

public class BlockPaleHangingMoss extends BlockRoots {

    public BlockPaleHangingMoss() {
        this(0);
    }

    public BlockPaleHangingMoss(int meta) {
        super(meta);
    }

    @Override
    protected boolean isSupportValid() {
        Block up = this.up();
        return up.isSolid() && !up.isTransparent();
    }

    @Override
    public String getName() {
        return "Pale Hanging Moss";
    }

    @Override
    public int getId() {
        return PALE_HANGING_MOSS;
    }
}
