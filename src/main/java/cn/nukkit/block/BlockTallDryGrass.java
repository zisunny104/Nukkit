package cn.nukkit.block;

public class BlockTallDryGrass extends BlockFlowable {

    public BlockTallDryGrass() {
        this(0);
    }

    public BlockTallDryGrass(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return TALL_DRY_GRASS;
    }

    @Override
    public String getName() {
        return "Tall Dry Grass";
    }
}
