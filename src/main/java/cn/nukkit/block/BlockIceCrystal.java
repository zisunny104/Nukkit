package cn.nukkit.block;

public class BlockIceCrystal extends BlockTransparentMeta {

    public BlockIceCrystal() {
        this(0);
    }

    public BlockIceCrystal(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return ICE_CRYSTAL;
    }

    @Override
    public String getName() {
        return "Ice Crystal";
    }
}
