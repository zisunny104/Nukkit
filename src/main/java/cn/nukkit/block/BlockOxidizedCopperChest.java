package cn.nukkit.block;

public class BlockOxidizedCopperChest extends BlockChest {

    public BlockOxidizedCopperChest() {
        this(0);
    }

    public BlockOxidizedCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return OXIDIZED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Oxidized Copper Chest";
    }

    @Override
    protected boolean canPairWith(Block other) {
        return other.getId() == this.getId();
    }
}
