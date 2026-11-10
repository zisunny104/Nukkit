package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.math.BlockFace;

public class BlockPoplarWood extends BlockWood {

    public BlockPoplarWood() {
        this(0);
    }

    public BlockPoplarWood(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Wood";
    }

    @Override
    public int getId() {
        return POPLAR_WOOD;
    }

    @Override
    protected int getStrippedId() {
        return STRIPPED_POPLAR_WOOD;
    }

    @Override
    protected int getStrippedDamage() {
        return 0; //getDamage();
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        //this.setPillarAxis(face.getAxis());
        return this.getLevel().setBlock(block, this, true, true);
    }
}
