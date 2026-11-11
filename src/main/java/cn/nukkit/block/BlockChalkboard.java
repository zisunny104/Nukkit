package cn.nukkit.block;

public class BlockChalkboard extends BlockTransparentMeta {

    public BlockChalkboard() {
        this(0);
    }

    public BlockChalkboard(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CHALKBOARD;
    }

    @Override
    public String getName() {
        return "Chalkboard";
    }
}
