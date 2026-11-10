package cn.nukkit.block;

public class BlockResinClump extends BlockFlowable {

    public BlockResinClump() {
        this(0);
    }

    public BlockResinClump(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return RESIN_CLUMP;
    }

    @Override
    public String getName() {
        return "Resin Clump";
    }
}
