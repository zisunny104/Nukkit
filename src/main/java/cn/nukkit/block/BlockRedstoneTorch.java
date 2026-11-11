package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.event.redstone.RedstoneUpdateEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.Faceable;

/**
 * @author Angelic47
 * Nukkit Project
 */
public class BlockRedstoneTorch extends BlockTorch implements Faceable {

    public BlockRedstoneTorch() {
        this(0);
    }

    public BlockRedstoneTorch(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Redstone Torch";
    }

    @Override
    public int getId() {
        return REDSTONE_TORCH;
    }

    @Override
    public int getLightLevel() {
        return 7;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        if (!super.place(item, block, target, face, fx, fy, fz, player)) {
            return false;
        }

        checkState();

        return true;
    }

    @Override
    public int getWeakPower(BlockFace side) {
        return getBlockFace() != side ? 15 : 0;
    }

    @Override
    public int getStrongPower(BlockFace side) {
        return side == BlockFace.DOWN ? this.getWeakPower(side) : 0;
    }

    @Override
    public boolean onBreak(Item item) {
        super.onBreak(item);

        BlockFace face = getBlockFace().getOpposite();

        for (BlockFace side : BlockFace.values()) {
            if (side == face) {
                continue;
            }

            this.level.updateAroundRedstone(this.getSideVec(side), null);
        }
        return true;
    }

    @Override
    public int onUpdate(int type) {
        if (super.onUpdate(type) == 0) {
            if (type == Level.BLOCK_UPDATE_NORMAL || type == Level.BLOCK_UPDATE_REDSTONE) {
                this.level.scheduleUpdate(this, tickRate());
            } else if (type == Level.BLOCK_UPDATE_SCHEDULED) {
                RedstoneUpdateEvent ev = new RedstoneUpdateEvent(this);
                getLevel().getServer().getPluginManager().callEvent(ev);

                if (ev.isCancelled()) {
                    return 0;
                }

                if (checkState()) {
                    return 1;
                }
            }
        }

        return 0;
    }

    /** Turn-off ticks of the torches that switched recently, per level and position. */
    private static final java.util.Map<Level, it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap<it.unimi.dsi.fastutil.ints.IntArrayList>> TOGGLES =
            java.util.Collections.synchronizedMap(new java.util.WeakHashMap<>());
    private static final int BURNOUT_TOGGLES = 8;
    private static final int BURNOUT_WINDOW = 60;

    /**
     * A torch that turned off 8 times within 60 ticks burns out and stays off until it cools down.
     *
     * @param record true when the torch turns off now
     */
    static boolean isBurnedOut(Block b, boolean record) {
        int now = (int) b.level.getCurrentTick();
        long key = (((long) b.getFloorX() & 0x3FFFFFF) << 38) | (((long) b.getFloorZ() & 0x3FFFFFF) << 12) | (b.getFloorY() & 0xFFF);
        it.unimi.dsi.fastutil.ints.IntArrayList list;
        synchronized (TOGGLES) {
            it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap<it.unimi.dsi.fastutil.ints.IntArrayList> map =
                    TOGGLES.computeIfAbsent(b.level, l -> new it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap<>());
            list = map.get(key);
            if (list == null) {
                if (!record) {
                    return false;
                }
                list = new it.unimi.dsi.fastutil.ints.IntArrayList();
                map.put(key, list);
            }
            if (record) {
                list.add(now);
            }
            while (!list.isEmpty() && now - list.getInt(0) > BURNOUT_WINDOW) {
                list.removeInt(0);
            }
            if (list.isEmpty()) {
                map.remove(key);
                return false;
            }
            return list.size() >= BURNOUT_TOGGLES;
        }
    }

    protected boolean checkState() {
        if (isPoweredFromSide()) {
            BlockFace face = getBlockFace().getOpposite();
            isBurnedOut(this, true);

            this.level.setBlock(this, Block.get(UNLIT_REDSTONE_TORCH, getDamage()), false, true);

            for (BlockFace side : BlockFace.values()) {
                if (side == face) {
                    continue;
                }

                this.level.updateAroundRedstone(this.getSideVec(side), null);
            }

            return true;
        }

        return false;
    }

    protected boolean isPoweredFromSide() {
        BlockFace face = getBlockFace().getOpposite();
        return this.level.isSidePowered(this.getSideVec(face), face);
    }
     @Override
    public int tickRate() {
        return 2;
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.AIR_BLOCK_COLOR;
    }
}