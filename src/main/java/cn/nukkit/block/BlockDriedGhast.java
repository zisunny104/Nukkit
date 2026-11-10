package cn.nukkit.block;

public class BlockDriedGhast extends BlockTransparentMeta {

    public BlockDriedGhast() {
        this(0);
    }

    public BlockDriedGhast(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return DRIED_GHAST;
    }

    @Override
    public String getName() {
        return "Dried Ghast";
    }
}
