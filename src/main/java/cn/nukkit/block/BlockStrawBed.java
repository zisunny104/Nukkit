package cn.nukkit.block;

public class BlockStrawBed extends BlockTransparentMeta {

    public BlockStrawBed() {
        this(0);
    }

    public BlockStrawBed(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return STRAW_BED;
    }

    @Override
    public String getName() {
        return "Straw Bed";
    }
}
