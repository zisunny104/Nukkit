package cn.nukkit.block;

public class BlockPotentSulfur extends BlockSolidMeta {

    public BlockPotentSulfur() {
        this(0);
    }

    public BlockPotentSulfur(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return POTENT_SULFUR;
    }

    @Override
    public String getName() {
        return "Potent Sulfur";
    }
}
