package cn.nukkit.entity.passive;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.math.Vector3;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.Utils;

public class EntityNautilus extends EntityWalkingAnimal {

    public static final int NETWORK_ID = 149;

    public EntityNautilus(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
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
        this.setMaxHealth(15);
        super.initEntity();
        this.gear = new MountGear(this, MountGear.NAUTILUS_ARMOR, true);
        this.gear.load(this.namedTag);
    }

    @Override
    public float getWidth() {
        return this.isBaby() ? 0.44f : 0.875f;
    }

    @Override
    public float getHeight() {
        return this.isBaby() ? 0.5f : 0.95f;
    }

    @Override
    public Item[] getDrops() {
        java.util.List<Item> drops = new java.util.ArrayList<>();
        if (!this.isBaby()) {
            drops.add(Item.get(Item.NAUTILUS_SHELL, 0, Utils.rand(0, 1)));
        }
        if (this.gear != null) {
            this.gear.drops(drops);
        }
        return drops.toArray(new Item[0]);
    }

    @Override
    public int getKillExperience() {
        return this.isBaby() ? 0 : Utils.rand(1, 3);
    }
}
