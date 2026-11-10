package cn.nukkit.block;

public class BlockShortDryGrass extends BlockFlowable {

    public BlockShortDryGrass() {
        this(0);
    }

    public BlockShortDryGrass(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return SHORT_DRY_GRASS;
    }

    @Override
    public String getName() {
        return "Short Dry Grass";
    }
}
