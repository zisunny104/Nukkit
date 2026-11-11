package cn.nukkit.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;

public abstract class EntityHorseBase extends EntityWalkingAnimal /*implements EntityRideable, EntityControllable*/ {

    public EntityHorseBase(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getKillExperience() {
        return 0;
    }

    protected MountGear gear;

    /** Body armor items this animal accepts (horse armor for horses). */
    protected java.util.function.IntPredicate bodyItems() {
        return id -> false;
    }

    @Override
    public void initEntity() {
        super.initEntity();
        this.gear = new MountGear(this, this.bodyItems(), true);
        this.gear.load(this.namedTag);
    }

    @Override
    public void saveNBT() {
        super.saveNBT();
        if (this.gear != null) {
            this.gear.save(this.namedTag);
        }
    }

    @Override
    public void spawnTo(Player player) {
        super.spawnTo(player);
        if (this.gear != null) {
            this.gear.sendTo(player);
        }
    }

    @Override
    public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
        if (this.gear != null && !this.isBaby() && this.gear.interact(player, item)) {
            return true;
        }
        return super.onInteract(player, item, clickedPos);
    }

    protected Item[] addGearDrops(java.util.List<Item> drops) {
        if (this.gear != null) {
            this.gear.drops(drops);
        }
        return drops.toArray(new Item[0]);
    }
}
