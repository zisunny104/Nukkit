package cn.nukkit.block;

public class BlockSulfurSpike extends BlockTransparentMeta {

    public BlockSulfurSpike() {
        this(0);
    }

    public BlockSulfurSpike(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return SULFUR_SPIKE;
    }

    @Override
    public String getName() {
        return "Sulfur Spike";
    }
}
