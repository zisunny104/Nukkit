package cn.nukkit.block;

public class BlockWildflowers extends BlockPinkPetals {

    public BlockWildflowers() {
        this(0);
    }

    public BlockWildflowers(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WILDFLOWERS;
    }

    @Override
    public String getName() {
        return "Wildflowers";
    }
}
