package cn.nukkit.block;

public class BlockOpenEyeblossom extends BlockGroundPlant {

    public BlockOpenEyeblossom() {
        this(0);
    }

    public BlockOpenEyeblossom(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return OPEN_EYEBLOSSOM;
    }

    @Override
    public String getName() {
        return "Open Eyeblossom";
    }
}
