package cn.nukkit.block;

public class BlockOxidizedCopperGolemStatue extends BlockTransparentMeta {

    public BlockOxidizedCopperGolemStatue() {
        this(0);
    }

    public BlockOxidizedCopperGolemStatue(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return OXIDIZED_COPPER_GOLEM_STATUE;
    }

    @Override
    public String getName() {
        return "Oxidized Copper Golem Statue";
    }
}
