package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.block.properties.OxidizationLevel;
import cn.nukkit.item.Item;

/**
 * A copper block kind that oxidizes over time and can be waxed, the block ids are listed in {@link CopperFamilies}.
 */
public interface CopperFamily extends Oxidizable, Waxable {

    int[] getFamilyIds();

    int getId();

    int getDamage();

    @Override
    default boolean onActivate(Item item, Player player) {
        return Waxable.super.onActivate(item, player) || Oxidizable.super.onActivate(item, player);
    }

    @Override
    default OxidizationLevel getOxidizationLevel() {
        return OxidizationLevel.values()[this.indexInFamily() % 4];
    }

    @Override
    default boolean isWaxed() {
        return this.indexInFamily() >= 4;
    }

    @Override
    default Block getStateWithOxidizationLevel(OxidizationLevel level) {
        return Block.get(this.getFamilyIds()[(this.isWaxed() ? 4 : 0) + level.ordinal()], this.getDamage());
    }

    @Override
    default boolean setOxidizationLevel(OxidizationLevel level) {
        Block self = (Block) this;
        return level == this.getOxidizationLevel() || self.getLevel().setBlock(self, this.getStateWithOxidizationLevel(level));
    }

    @Override
    default boolean setWaxed(boolean waxed) {
        if (waxed == this.isWaxed()) {
            return true;
        }
        Block self = (Block) this;
        int index = this.indexInFamily() % 4 + (waxed ? 4 : 0);
        return self.getLevel().setBlock(self, Block.get(this.getFamilyIds()[index], this.getDamage()));
    }

    private int indexInFamily() {
        int[] ids = this.getFamilyIds();
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == this.getId()) {
                return i;
            }
        }
        return 0;
    }
}
