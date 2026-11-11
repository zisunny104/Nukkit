package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;

public class BlockLightningRod extends BlockTransparentMeta implements CopperFamily {

    private static final int[] FACES = {0, 1, 2, 3, 4, 5};

    public BlockLightningRod() {
        this(0);
    }

    public BlockLightningRod(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Lightning Rod";
    }

    @Override
    public int getId() {
        return LIGHTNING_ROD;
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public double getResistance() {
        return 6;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public double getMinX() {
        return this.x + 0.4;
    }

    @Override
    public double getMinZ() {
        return this.z + 0.4;
    }

    @Override
    public double getMaxX() {
        return this.x + 0.6;
    }

    @Override
    public double getMaxZ() {
        return this.z + 0.6;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        this.setDamage(FACES[player != null ? face.getIndex() : 0]);
        this.getLevel().setBlock(this, this, true, true);
        return true;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    @Override
    public Item[] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= ItemTool.TIER_STONE) {
            return new Item[]{
                    toItem()
            };
        } else {
            return new Item[0];
        }
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public int[] getFamilyIds() {
        return CopperFamilies.LIGHTNING_ROD;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        return CopperFamily.super.onActivate(item, player) || super.onActivate(item, player);
    }

    @Override
    public int onUpdate(int type) {
        if (type == cn.nukkit.level.Level.BLOCK_UPDATE_SCHEDULED) {
            // power ran out
            if (!this.isStruck()) {
                this.level.updateAroundRedstone(this, null);
                this.level.updateAroundRedstone(this.getSide(this.getFacing().getOpposite()), null);
            }
            return type;
        }
        int result = CopperFamily.super.onUpdate(type);
        return result != 0 ? result : super.onUpdate(type);
    }

    /**
     * Lightning rods are powered for 8 ticks after a strike. The state is kept in memory only
     * (the power bit is not part of the block meta mapping yet).
     */
    private static final java.util.Map<Long, Long> STRUCK_UNTIL = new java.util.concurrent.ConcurrentHashMap<>();

    private long key() {
        return ((long) this.getFloorX() & 0x3ffffffL) << 38 | ((long) this.getFloorZ() & 0x3ffffffL) << 12 | ((long) this.getFloorY() & 0xfffL);
    }

    public boolean isStruck() {
        Long until = STRUCK_UNTIL.get(this.key());
        if (until == null) {
            return false;
        }
        if (until <= this.level.getCurrentTick()) {
            STRUCK_UNTIL.remove(this.key());
            return false;
        }
        return true;
    }

    public void strike() {
        STRUCK_UNTIL.put(this.key(), this.level.getCurrentTick() + 8);
        this.level.updateAroundRedstone(this, null);
        this.level.updateAroundRedstone(this.getSide(this.getFacing().getOpposite()), null);
        this.level.scheduleUpdate(this, 8);
    }

    public BlockFace getFacing() {
        return BlockFace.fromIndex(this.getDamage() & 0x7);
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    @Override
    public int getWeakPower(BlockFace face) {
        return this.isStruck() ? 15 : 0;
    }

    @Override
    public int getStrongPower(BlockFace face) {
        return this.isStruck() && face == this.getFacing() ? 15 : 0;
    }
}
