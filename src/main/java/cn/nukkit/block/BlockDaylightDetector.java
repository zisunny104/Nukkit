package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;

/**
 * Created on 2015/11/22 by CreeperFace.
 * Package cn.nukkit.block in project Nukkit .
 */
public class BlockDaylightDetector extends BlockTransparent {

    @Override
    public int getId() {
        return DAYLIGHT_DETECTOR;
    }

    @Override
    public String getName() {
        return "Daylight Detector";
    }

    @Override
    public double getHardness() {
        return 0.2;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.WOOD_BLOCK_COLOR;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        this.getLevel().setBlock(this, Block.get(DAYLIGHT_DETECTOR_INVERTED));
        return true;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    @Override
    public boolean isPowerSource() {
        return true;
    }

    @Override
    public int getWeakPower(BlockFace face) {
        return this.getSunPower();
    }

    /**
     * Signal strength follows the sun angle (0-15), reduced by rain and thunder.
     */
    protected int getSunPower() {
        double d0 = ((level.getTime() % Level.TIME_FULL) / (double) Level.TIME_FULL - 0.25D);
        d0 -= Math.floor(d0);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        double angle = (d0 + (d1 - d0) / 3.0D) * 2.0D * Math.PI;
        int sky = 15;
        if (level.isThundering()) {
            sky = 10;
        } else if (level.isRaining()) {
            sky = 12;
        }
        double f1 = angle < Math.PI ? 0.0D : 2.0D * Math.PI;
        angle += (f1 - angle) * 0.2D;
        int power = (int) Math.round(sky * Math.cos(angle));
        return Math.max(0, Math.min(15, power));
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public double getMaxY() {
        return this.y + 0.625;
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL || type == Level.BLOCK_UPDATE_SCHEDULED) {
            if (type == Level.BLOCK_UPDATE_SCHEDULED) {
                this.level.updateAroundRedstone(this, null);
            }
            this.level.scheduleUpdate(this, 20);
        }
        return 0;
    }

    @Override
    public WaterloggingType getWaterloggingType() {
        return WaterloggingType.WHEN_PLACED_IN_WATER;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        if (this.getLevel().setBlock(this, this, true, true)) {
            this.level.scheduleUpdate(this, 20);
            return true;
        }
        return false;
    }
}
