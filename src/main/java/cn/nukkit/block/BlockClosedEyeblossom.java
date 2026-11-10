package cn.nukkit.block;

public class BlockClosedEyeblossom extends BlockFlowable {

    public BlockClosedEyeblossom() {
        this(0);
    }

    public BlockClosedEyeblossom(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CLOSED_EYEBLOSSOM;
    }

    @Override
    public String getName() {
        return "Closed Eyeblossom";
    }
}
