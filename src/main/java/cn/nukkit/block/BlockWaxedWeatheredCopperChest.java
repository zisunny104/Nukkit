package cn.nukkit.block;

public class BlockWaxedWeatheredCopperChest extends BlockTransparentMeta {

    public BlockWaxedWeatheredCopperChest() {
        this(0);
    }

    public BlockWaxedWeatheredCopperChest(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return WAXED_WEATHERED_COPPER_CHEST;
    }

    @Override
    public String getName() {
        return "Waxed Weathered Copper Chest";
    }
}
