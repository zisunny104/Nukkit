package cn.nukkit.block;

import cn.nukkit.Server;
import cn.nukkit.event.block.BlockGrowEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.BlockFace.Plane;
import cn.nukkit.utils.Utils;

/**
 * Created by Pub4Game on 15.01.2016.
 */
public class BlockStemMelon extends BlockCrops {

    public BlockStemMelon() {
        this(0);
    }

    public BlockStemMelon(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return MELON_STEM;
    }

    @Override
    public String getName() {
        return "Melon Stem";
    }

    /** Data bits 0-2 are the growth, bits 3-5 the facing_direction toward the fruit (0 while there is none). */
    private static final int GROWTH_MASK = 0x07;

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (this.down().getId() != FARMLAND) {
                this.getLevel().useBreakOn(this);
                return Level.BLOCK_UPDATE_NORMAL;
            }
        } else if (type == Level.BLOCK_UPDATE_RANDOM) {
            if (Utils.rand()) {
                if ((this.getDamage() & GROWTH_MASK) < 0x07) {
                    Block block = this.clone();
                    block.setDamage(block.getDamage() + 1);
                    BlockGrowEvent ev = new BlockGrowEvent(this, block);
                    Server.getInstance().getPluginManager().callEvent(ev);
                    if (!ev.isCancelled()) {
                        this.getLevel().setBlock(this, ev.getNewState(), true);
                    }
                    return Level.BLOCK_UPDATE_RANDOM;
                } else {
                    for (BlockFace face : Plane.HORIZONTAL) {
                        Block b = this.getSide(face);
                        if (b.getId() == MELON_BLOCK) {
                            return Level.BLOCK_UPDATE_RANDOM;
                        }
                    }
                    BlockFace sideFace = Plane.HORIZONTAL.random();
                    Block side = this.getSide(sideFace);
                    Block d;
                    if (side.getId() == AIR && ((d = side.down()).getId() == FARMLAND || d.getId() == GRASS || d.getId() == DIRT)) {
                        BlockGrowEvent ev = new BlockGrowEvent(side, Block.get(MELON_BLOCK));
                        Server.getInstance().getPluginManager().callEvent(ev);
                        if (!ev.isCancelled()) {
                            this.getLevel().setBlock(side, ev.getNewState(), true);
                            // the stem leans toward its fruit (data bits 3-5 are the facing_direction)
                            this.setDamage((this.getDamage() & GROWTH_MASK) | (sideFace.getIndex() << 3));
                            this.getLevel().setBlock(this, this, true);
                        }
                    }
                }
            }
            return Level.BLOCK_UPDATE_RANDOM;
        }
        return 0;
    }

    @Override
    public Item toItem() {
        return Item.get(Item.MELON_SEEDS);
    }

    @Override
    public Item[] getDrops(Item item) {
        if ((this.getDamage() & GROWTH_MASK) < 4) return new Item[0];
        return new Item[]{
                Item.get(Item.MELON_SEEDS, 0, Utils.rand(0, 48) >> 4)
        };
    }

    @Override
    public boolean breakWhenPushed() {
        return true;
    }
}
