package cn.nukkit.block;

public class BlockShelfMushroom extends BlockGroundPlant {

    public BlockShelfMushroom() {
        this(0);
    }

    public BlockShelfMushroom(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return SHELF_MUSHROOM;
    }

    @Override
    public String getName() {
        return "Shelf Mushroom";
    }
}
