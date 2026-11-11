package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.Utils;

public class BlockChorusPlant extends BlockTransparentMeta {

    public BlockChorusPlant() {
        this(0);
    }

    public BlockChorusPlant(int meta) {
        super(meta);
    }

    /** Meta bits are the official connection states: down 1, up 2, north 4, south 8, east 16, west 32. */
    private static final BlockFace[] SIDES = {BlockFace.DOWN, BlockFace.UP, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};

    /** A side connects to a chorus plant or flower; the bottom also to end stone (confirmed by the BDS capture). */
    private int computeConnections() {
        int meta = 0;
        for (int i = 0; i < SIDES.length; i++) {
            int id = getSide(SIDES[i]).getId();
            if (id == CHORUS_PLANT || id == CHORUS_FLOWER || (i == 0 && id == END_STONE)) {
                meta |= 1 << i;
            }
        }
        return meta;
    }

    private void refreshConnections() {
        int meta = computeConnections();
        if (meta != getDamage()) {
            setDamage(meta);
            this.getLevel().setBlock(this, this, true, true);
        }
    }

    @Override
    public Item toItem() {
        return new cn.nukkit.item.ItemBlock(Block.get(getId(), 0), 0);
    }

    @Override
    public int getId() {
        return CHORUS_PLANT;
    }

    @Override
    public String getName() {
        return "Chorus Plant";
    }

    @Override
    public double getHardness() {
        return 0.4;
    }

    @Override
    public double getResistance() {
        return 0.4;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        if (!isSupportValid()) {
            return false;
        }
        this.setDamage(computeConnections());
        return super.place(item, block, target, face, fx, fy, fz, player);
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    private boolean isSupportValid() {
        // Must be on end stone or chorus plant, or be above air and horizontally adjacent to exactly one chorus plant, otherwise breaks without dropping anything
        int down = down().getId();
        if (down == CHORUS_PLANT || down == END_STONE) {
            return true;
        }

        if (down != AIR) {
            return false;
        }

        boolean plantFound = false;
        for (BlockFace face : BlockFace.Plane.HORIZONTAL) {
            if (getSide(face).getId() == CHORUS_PLANT) {
                if (plantFound) {
                    return false;
                }
                plantFound = true;
            }
        }

        return plantFound;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            if (!isSupportValid()) {
                this.getLevel().scheduleUpdate(this, 1);
                return type;
            }
            refreshConnections();
        } else if (type == Level.BLOCK_UPDATE_SCHEDULED) {
            this.getLevel().useBreakOn(this, null, null, true);
            return type;
        }

        return 0;
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.hasEnchantment(Enchantment.ID_SILK_TOUCH)) {
            return new Item[]{this.toItem()};
        }
        return Utils.rand() ? new Item[]{Item.get(Item.CHORUS_FRUIT, 0, 1)} : new Item[0];
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.PURPLE_BLOCK_COLOR;
    }

    @Override
    public boolean breakWhenPushed() {
        return true;
    }
}
