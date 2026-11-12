package cn.nukkit.blockentity;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockTrialSpawner;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.nbt.tag.StringTag;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.SimpleLootTable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simplified trial spawner: waits for players within the range, spawns waves of mobs around itself
 * (more with more players), then ejects consumables and one key per player and cools down.
 * Wave progress is kept in memory only; the block state is the persistent part.
 */
public class BlockEntityTrialSpawner extends BlockEntity {

    private static final int DEFAULT_RANGE = 14;
    private static final int SPAWN_RANGE = 4;
    private static final int COOLDOWN_TICKS = 36000;
    private static final String[] NORMAL_MOBS = {"Zombie", "Skeleton", "Spider", "CaveSpider", "Silverfish"};
    private static final String[] OMINOUS_MOBS = {"Zombie", "Skeleton", "Spider", "Breeze", "Bogged"};

    private final Set<String> detected = new HashSet<>();
    private final List<Long> mobs = new ArrayList<>();
    private final Deque<Item> pending = new ArrayDeque<>();
    private int spawned;
    private int totalMobs;
    private int simultaneous;
    private int timer;

    public BlockEntityTrialSpawner(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    protected void initBlockEntity() {
        super.initBlockEntity();
        this.scheduleUpdate();
    }

    @Override
    public boolean isBlockEntityValid() {
        return level.getBlockIdAt(chunk, (int) x, (int) y, (int) z) == BlockID.TRIAL_SPAWNER;
    }

    private BlockTrialSpawner getSpawner() {
        Block block = this.level.getBlock(this);
        return block instanceof BlockTrialSpawner ? (BlockTrialSpawner) block : null;
    }

    private int range() {
        return this.namedTag.contains("required_player_range") ? this.namedTag.getInt("required_player_range") : DEFAULT_RANGE;
    }

    private List<Player> playersInRange() {
        List<Player> result = new ArrayList<>();
        Vector3 center = new Vector3(this.x + 0.5, this.y + 0.5, this.z + 0.5);
        double range = this.range();
        for (Player player : this.level.getPlayers().values()) {
            if (player.isAlive() && !player.isSpectator() && player.distanceSquared(center) <= range * range) {
                result.add(player);
            }
        }
        return result;
    }

    private String[] potentials(BlockTrialSpawner spawner) {
        if (this.namedTag.contains("spawn_potentials") && this.namedTag.get("spawn_potentials") instanceof ListTag) {
            List<StringTag> list = this.namedTag.getList("spawn_potentials", StringTag.class).getAll();
            if (!list.isEmpty()) {
                String[] names = new String[list.size()];
                for (int i = 0; i < names.length; i++) {
                    names[i] = list.get(i).data;
                }
                return names;
            }
        }
        return spawner.isOminous() ? OMINOUS_MOBS : NORMAL_MOBS;
    }

    private void startWave(BlockTrialSpawner spawner, List<Player> players) {
        boolean ominous = spawner.isOminous();
        int extra = Math.max(0, players.size() - 1);
        this.totalMobs = (ominous ? 12 : 6) + extra * (ominous ? 4 : 2);
        this.simultaneous = (ominous ? 4 : 2) + extra;
        this.spawned = 0;
        this.mobs.clear();
        this.timer = 0;
        spawner.setState(BlockTrialSpawner.STATE_ACTIVE);
    }

    private int aliveMobs() {
        int alive = 0;
        for (int i = mobs.size() - 1; i >= 0; i--) {
            Entity entity = this.level.getEntity(mobs.get(i));
            if (entity == null || entity.closed || !entity.isAlive()) {
                mobs.remove(i);
            } else {
                alive++;
            }
        }
        return alive;
    }

    private void spawnMob(BlockTrialSpawner spawner) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String[] names = this.potentials(spawner);

        for (int attempt = 0; attempt < 10; attempt++) {
            int bx = (int) this.x + random.nextInt(-SPAWN_RANGE, SPAWN_RANGE + 1);
            int bz = (int) this.z + random.nextInt(-SPAWN_RANGE, SPAWN_RANGE + 1);
            int by = (int) this.y + random.nextInt(-1, 3);

            if (this.level.getBlock(bx, by, bz).isSolid() || this.level.getBlock(bx, by + 1, bz).isSolid()
                    || !this.level.getBlock(bx, by - 1, bz).isSolid()) {
                continue;
            }

            Entity entity = Entity.createEntity(names[random.nextInt(names.length)], new Position(bx + 0.5, by, bz + 0.5, this.level));
            if (entity != null) {
                entity.spawnToAll();
                mobs.add(entity.getId());
                spawned++;
                return;
            }
        }
    }

    private void finishWave(BlockTrialSpawner spawner) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String base = spawner.isOminous() ? "spawners/ominous/trial_chamber/" : "spawners/trial_chamber/";
        pending.addAll(SimpleLootTable.roll(base + "consumables", random));
        for (int i = 0; i < Math.max(1, detected.size()); i++) {
            pending.addAll(SimpleLootTable.roll(base + "key", random));
        }
        this.timer = 0;
        spawner.setState(BlockTrialSpawner.STATE_WAITING_FOR_REWARD_EJECTION);
    }

    @Override
    public boolean onUpdate() {
        if (this.closed) {
            return false;
        }

        BlockTrialSpawner spawner = this.getSpawner();
        if (spawner == null) {
            return true;
        }

        this.timer++;

        switch (spawner.getState()) {
            case BlockTrialSpawner.STATE_INACTIVE:
                if (this.timer % 40 == 0) {
                    spawner.setState(BlockTrialSpawner.STATE_WAITING_FOR_PLAYERS);
                }
                break;
            case BlockTrialSpawner.STATE_WAITING_FOR_PLAYERS:
                if (this.timer % 10 == 0) {
                    List<Player> players = this.playersInRange();
                    if (!players.isEmpty()) {
                        detected.clear();
                        boolean ominous = spawner.isOminous();
                        for (Player player : players) {
                            detected.add(player.getUniqueId().toString());
                            if (!ominous && player.hasEffect(Effect.BAD_OMEN)) {
                                player.removeEffect(Effect.BAD_OMEN);
                                ominous = true;
                            }
                        }
                        if (ominous && !spawner.isOminous()) {
                            spawner.setStateAndOminous(BlockTrialSpawner.STATE_WAITING_FOR_PLAYERS, true);
                            spawner = this.getSpawner();
                        }
                        this.startWave(spawner, players);
                    }
                }
                break;
            case BlockTrialSpawner.STATE_ACTIVE:
                if (this.timer % 20 == 0) {
                    for (Player player : this.playersInRange()) {
                        detected.add(player.getUniqueId().toString());
                    }
                }
                if (this.timer % 40 == 0 && spawned < totalMobs && aliveMobs() < simultaneous) {
                    this.spawnMob(spawner);
                }
                if (this.timer % 20 == 0) {
                    int alive = this.aliveMobs();
                    if (spawned >= totalMobs && alive == 0) {
                        this.finishWave(spawner);
                    } else if (this.playersInRange().isEmpty()) {
                        // everybody left, forget the wave and cool down shortly
                        this.mobs.clear();
                        spawner.setState(BlockTrialSpawner.STATE_COOLDOWN);
                        this.timer = COOLDOWN_TICKS - 600;
                    }
                }
                break;
            case BlockTrialSpawner.STATE_WAITING_FOR_REWARD_EJECTION:
                if (this.timer >= 40) {
                    this.timer = 0;
                    spawner.setState(BlockTrialSpawner.STATE_EJECTING_REWARD);
                }
                break;
            case BlockTrialSpawner.STATE_EJECTING_REWARD:
                if (this.timer % 20 == 0) {
                    Item item = pending.poll();
                    if (item != null) {
                        this.level.dropItem(new Vector3(this.x + 0.5, this.y + 1.1, this.z + 0.5), item, new Vector3(0, 0.25, 0));
                    }
                    if (pending.isEmpty()) {
                        this.timer = 0;
                        spawner.setState(BlockTrialSpawner.STATE_COOLDOWN);
                    }
                }
                break;
            case BlockTrialSpawner.STATE_COOLDOWN:
                if (this.timer >= COOLDOWN_TICKS) {
                    this.timer = 0;
                    detected.clear();
                    spawner.setStateAndOminous(BlockTrialSpawner.STATE_WAITING_FOR_PLAYERS, false);
                }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onBreak() {
        for (Item item : this.pending) {
            this.level.dropItem(this, item);
        }
        this.pending.clear();
    }
}
