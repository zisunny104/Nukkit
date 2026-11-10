package cn.nukkit.block;

public class BlockCactusFlower extends BlockGroundPlant {

    public BlockCactusFlower() {
        this(0);
    }

    public BlockCactusFlower(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CACTUS_FLOWER;
    }

    @Override
    public String getName() {
        return "Cactus Flower";
    }

    @Override
    protected boolean canStandOn(Block down) {
        return down.getId() == CACTUS;
    }
}
