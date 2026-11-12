package cn.nukkit.blockentity;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.block.BlockVault;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.nbt.tag.StringTag;
import cn.nukkit.network.protocol.LevelSoundEventPacket;
import cn.nukkit.utils.SimpleLootTable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

public class BlockEntityVault extends BlockEntity {

    private static final double DETECTION_RANGE = 4.5;

    private final Deque<Item> pending = new ArrayDeque<>();
    private int timer;

    public BlockEntityVault(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    protected void initBlockEntity() {
        super.initBlockEntity();
        this.scheduleUpdate();
    }

    @Override
    public boolean isBlockEntityValid() {
        return level.getBlockIdAt(chunk, (int) x, (int) y, (int) z) == BlockID.VAULT;
    }

    public boolean hasRewarded(Player player) {
        if (!this.namedTag.contains("rewarded_players")) {
            return false;
        }
        String id = player.getUniqueId().toString();
        for (StringTag tag : this.namedTag.getList("rewarded_players", StringTag.class).getAll()) {
            if (tag.data.equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Starts the unlock animation and queues the loot for the player.
     */
    public void unlock(Player player, boolean ominous) {
        ListTag<StringTag> list = this.namedTag.contains("rewarded_players")
                ? this.namedTag.getList("rewarded_players", StringTag.class) : new ListTag<>("rewarded_players");
        list.add(new StringTag("", player.getUniqueId().toString()));
        this.namedTag.putList(list);

        this.pending.addAll(SimpleLootTable.roll(ominous ? "chests/trial_chambers/reward_ominous" : "chests/trial_chambers/reward", ThreadLocalRandom.current()));
        this.timer = 0;
        this.setState(BlockVault.STATE_UNLOCKING);
        this.level.addLevelSoundEvent(this, LevelSoundEventPacket.SOUND_VAULT_OPEN_SHUTTER);
        this.chunk.setChanged();
    }

    private BlockVault getVault() {
        Block block = this.level.getBlock(this);
        return block instanceof BlockVault ? (BlockVault) block : null;
    }

    private void setState(int state) {
        BlockVault vault = this.getVault();
        if (vault != null) {
            vault.setState(state);
        }
    }

    private boolean playerNearby() {
        for (Player player : this.level.getPlayers().values()) {
            if (player.isAlive() && !player.isSpectator() && player.distanceSquared(new Vector3(this.x + 0.5, this.y + 0.5, this.z + 0.5)) <= DETECTION_RANGE * DETECTION_RANGE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onUpdate() {
        if (this.closed) {
            return false;
        }

        BlockVault vault = this.getVault();
        if (vault == null) {
            return true;
        }

        int state = vault.getState();
        this.timer++;

        switch (state) {
            case BlockVault.STATE_INACTIVE:
                if (this.timer % 10 == 0 && this.playerNearby()) {
                    vault.setState(BlockVault.STATE_ACTIVE);
                }
                break;
            case BlockVault.STATE_ACTIVE:
                if (this.timer % 10 == 0 && !this.playerNearby()) {
                    vault.setState(BlockVault.STATE_INACTIVE);
                }
                break;
            case BlockVault.STATE_UNLOCKING:
                if (this.timer >= 20) {
                    this.timer = 0;
                    vault.setState(BlockVault.STATE_EJECTING);
                }
                break;
            case BlockVault.STATE_EJECTING:
                if (this.timer % 20 == 0) {
                    Item item = this.pending.poll();
                    if (item != null) {
                        this.level.dropItem(new Vector3(this.x + 0.5, this.y + 1.1, this.z + 0.5), item, new Vector3(0, 0.25, 0));
                        this.level.addLevelSoundEvent(this, LevelSoundEventPacket.SOUND_VAULT_EJECT_ITEM);
                    }
                    if (this.pending.isEmpty()) {
                        this.timer = 0;
                        vault.setState(this.playerNearby() ? BlockVault.STATE_ACTIVE : BlockVault.STATE_INACTIVE);
                        this.level.addLevelSoundEvent(this, LevelSoundEventPacket.SOUND_VAULT_CLOSE_SHUTTER);
                    }
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
