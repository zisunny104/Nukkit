package cn.nukkit.block;

public class BlockWallCinnabar extends BlockWallIndependentID {

    public BlockWallCinnabar() {
        this(0);
    }

    public BlockWallCinnabar(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Cinnabar Wall";
    }

    @Override
    public int getId() {
        return CINNABAR_WALL;
    }
}
