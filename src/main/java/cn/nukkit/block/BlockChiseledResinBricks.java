package cn.nukkit.block;

public class BlockChiseledResinBricks extends BlockSolidMeta {

    public BlockChiseledResinBricks() {
        this(0);
    }

    public BlockChiseledResinBricks(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CHISELED_RESIN_BRICKS;
    }

    @Override
    public String getName() {
        return "Chiseled Resin Bricks";
    }
}
