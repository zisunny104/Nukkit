package cn.nukkit.block;

public class BlockWallSulfurBrick extends BlockWallIndependentID {

    public BlockWallSulfurBrick() {
        this(0);
    }

    public BlockWallSulfurBrick(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Sulfur Brick Wall";
    }

    @Override
    public int getId() {
        return SULFUR_BRICK_WALL;
    }
}
