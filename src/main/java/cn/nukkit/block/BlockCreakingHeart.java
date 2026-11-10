package cn.nukkit.block;

public class BlockCreakingHeart extends BlockSolidMeta {

    public BlockCreakingHeart() {
        this(0);
    }

    public BlockCreakingHeart(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CREAKING_HEART;
    }

    @Override
    public String getName() {
        return "Creaking Heart";
    }
}
