package cn.nukkit.block;

public class BlockGoldenDandelion extends BlockFlowable {

    public BlockGoldenDandelion() {
        this(0);
    }

    public BlockGoldenDandelion(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return GOLDEN_DANDELION;
    }

    @Override
    public String getName() {
        return "Golden Dandelion";
    }
}
