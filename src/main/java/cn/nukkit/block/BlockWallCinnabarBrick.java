package cn.nukkit.block;

public class BlockWallCinnabarBrick extends BlockWallIndependentID {

    public BlockWallCinnabarBrick() {
        this(0);
    }

    public BlockWallCinnabarBrick(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Cinnabar Brick Wall";
    }

    @Override
    public int getId() {
        return CINNABAR_BRICK_WALL;
    }
}
