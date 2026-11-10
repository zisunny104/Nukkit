package cn.nukkit.block;

public class BlockTallDryGrass extends BlockGroundPlant {

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

    // TODO: two block high plant, the upper half is not placed yet
    @Override
    protected boolean canStandOn(Block down) {
        int id = down.getId();
        return super.canStandOn(down) || id == SAND || id == HARDENED_CLAY || id == STAINED_HARDENED_CLAY;
    }
}
