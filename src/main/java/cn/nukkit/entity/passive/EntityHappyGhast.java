package cn.nukkit.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.Utils;

public class EntityHappyGhast extends EntityFlyingAnimal {

    public static final int NETWORK_ID = 147;

    public EntityHappyGhast(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }

    @Override
    public float getWidth() {
        if (this.isBaby()) {
            return 0.95f;
        }
        return 4f;
    }

    @Override
    public float getHeight() {
        if (this.isBaby()) {
            return 0.95f;
        }
        return 4f;
    }

    private MountGear gear;

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

    @Override
    public void initEntity() {
        this.setMaxHealth(20);
        super.initEntity();
        this.gear = new MountGear(this, MountGear.HARNESS, false);
        this.gear.load(this.namedTag);
    }

    @Override
    public int getKillExperience() {
        return this.isBaby() ? 0 : Utils.rand(2, 3);
    }

    @Override
    public Item[] getDrops() {
        java.util.List<Item> drops = new java.util.ArrayList<>();
        if (this.gear != null) {
            this.gear.drops(drops);
        }
        return drops.toArray(new Item[0]);
    }
}
