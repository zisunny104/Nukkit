package cn.nukkit.item;

public class ItemCopperNugget extends Item {

    public ItemCopperNugget() {
        this(0, 1);
    }

    public ItemCopperNugget(Integer meta) {
        this(meta, 1);
    }

    public ItemCopperNugget(Integer meta, int count) {
        super(COPPER_NUGGET, meta, count, "Copper Nugget");
    }
}
