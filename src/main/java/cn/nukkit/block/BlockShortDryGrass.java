package cn.nukkit.block;

public class BlockShortDryGrass extends BlockGroundPlant {

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

    @Override
    protected boolean canStandOn(Block down) {
        int id = down.getId();
        return super.canStandOn(down) || id == SAND || id == HARDENED_CLAY || id == STAINED_HARDENED_CLAY;
    }
}
