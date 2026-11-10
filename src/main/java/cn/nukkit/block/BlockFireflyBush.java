package cn.nukkit.block;

public class BlockFireflyBush extends BlockFlowable {

    public BlockFireflyBush() {
        this(0);
    }

    public BlockFireflyBush(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return FIREFLY_BUSH;
    }

    @Override
    public String getName() {
        return "Firefly Bush";
    }
}
