package cn.nukkit.block;

public class BlockWallSulfur extends BlockWallIndependentID {

    public BlockWallSulfur() {
        this(0);
    }

    public BlockWallSulfur(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Sulfur Wall";
    }

    @Override
    public int getId() {
        return SULFUR_WALL;
    }
}
