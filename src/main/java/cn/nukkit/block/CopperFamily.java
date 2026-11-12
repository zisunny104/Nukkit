package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.block.properties.OxidizationLevel;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.blockentity.BlockEntitySpawnable;
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
        return level == this.getOxidizationLevel() || this.replaceKeepingBlockEntity(this.getStateWithOxidizationLevel(level));
    }

    /**
     * The client drops the block entity of a block that is replaced, so a statue lost its pose until the chunk was sent again.
     */
    private boolean replaceKeepingBlockEntity(Block replacement) {
        Block self = (Block) this;
        if (!self.getLevel().setBlock(self, replacement)) {
            return false;
        }
        cn.nukkit.blockentity.BlockEntity blockEntity = self.getLevel().getBlockEntity(self);
        if (blockEntity instanceof BlockEntitySpawnable) {
            ((BlockEntitySpawnable) blockEntity).spawnToAll();
        }
        return true;
    }

    @Override
    default boolean setWaxed(boolean waxed) {
        if (waxed == this.isWaxed()) {
            return true;
        }
        int index = this.indexInFamily() % 4 + (waxed ? 4 : 0);
        return this.replaceKeepingBlockEntity(Block.get(this.getFamilyIds()[index], this.getDamage()));
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
