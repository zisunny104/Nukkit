package cn.nukkit.block;

import cn.nukkit.block.properties.OxidizationLevel;
import cn.nukkit.level.Level;
import cn.nukkit.utils.BlockColor;


public class BlockCopperBulb extends BlockCopperBase {

    public BlockCopperBulb() {
        this(0);
    }

    public BlockCopperBulb(int meta) {
        super(meta);
    }

    /** Data bit 0 is lit, bit 1 powered. A rising edge of power toggles the light. */
    private static final int LIT = 0x01;
    private static final int POWERED = 0x02;

    public boolean isLit() {
        return (this.getDamage() & LIT) != 0;
    }

    @Override
    public int getLightLevel() {
        if (!this.isLit()) {
            return 0;
        }
        switch (this.getOxidizationLevel()) {
            case EXPOSED: return 12;
            case WEATHERED: return 8;
            case OXIDIZED: return 4;
            default: return 15;
        }
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL || type == Level.BLOCK_UPDATE_REDSTONE) {
            boolean powered = this.level.isBlockPowered(this);
            boolean wasPowered = (this.getDamage() & POWERED) != 0;
            if (powered != wasPowered) {
                int meta = powered ? (this.getDamage() | POWERED) : (this.getDamage() & ~POWERED);
                if (powered) {
                    meta ^= LIT;
                }
                this.setDamage(meta);
                this.level.setBlock(this, this, true, true);
            }
            return type;
        }
        return super.onUpdate(type);
    }

    @Override
    public String getName() {
        return "Copper Bulb";
    }

    @Override
    public int getId() {
        return COPPER_BULB;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.ORANGE_BLOCK_COLOR;
    }

    @Override
    public OxidizationLevel getOxidizationLevel() {
        return OxidizationLevel.UNAFFECTED;
    }

    @Override
    protected int getCopperId(boolean waxed, OxidizationLevel oxidizationLevel) {
        if (oxidizationLevel == null) {
            return this.getId();
        }

        switch (oxidizationLevel) {
            case UNAFFECTED:
                return waxed ? WAXED_COPPER_BULB : COPPER_BULB;
            case EXPOSED:
                return waxed ? WAXED_EXPOSED_COPPER_BULB : EXPOSED_COPPER_BULB;
            case WEATHERED:
                return waxed ? WAXED_WEATHERED_COPPER_BULB : WEATHERED_COPPER_BULB;
            case OXIDIZED:
                return waxed ? WAXED_OXIDIZED_COPPER_BULB : OXIDIZED_COPPER_BULB;
            default:
                return this.getId();
        }
    }
}
