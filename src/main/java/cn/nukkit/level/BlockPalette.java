package cn.nukkit.level;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.nbt.tag.CompoundTag;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntMaps;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Data
@Log4j2
public class BlockPalette {

    private final int protocol;
    private final Int2IntMap legacyToRuntimeId = new Int2IntOpenHashMap();
    private final Int2IntMap runtimeIdToLegacy = new Int2IntOpenHashMap();
    private final Map<CompoundTag, Integer> stateToLegacy = new HashMap<>();
    private final Int2ObjectMap<CompoundTag> legacyToState = new Int2ObjectOpenHashMap<>();
    // legacy ids by the position in the sorted vanilla palette, only filled when the runtime ids were remapped to network hashes
    private final Int2IntMap sequentialToLegacy = new Int2IntOpenHashMap();
    private volatile boolean locked;

    public BlockPalette(int protocol) {
        this.protocol = protocol;
        this.legacyToRuntimeId.defaultReturnValue(-1);
        this.runtimeIdToLegacy.defaultReturnValue(-1);
    }

    public void clearStates() {
        this.locked = false;
        this.legacyToRuntimeId.clear();
        this.runtimeIdToLegacy.clear();
        this.stateToLegacy.clear();
        this.legacyToState.clear();
    }

    public void registerState(int blockId, int data, int runtimeId, CompoundTag blockState) {
        this.registerState(blockId, data, runtimeId);
        this.stateToLegacy.putIfAbsent(blockState, blockId << Block.DATA_BITS | data);
        this.legacyToState.putIfAbsent(blockId << Block.DATA_BITS | data, blockState);
    }

    public void registerState(int blockId, int data, int runtimeId) {
        this.registerState(blockId, data, runtimeId, false);
    }

    public void registerState(int blockId, int data, int runtimeId, boolean replaceRuntimeMapping) {
        if (this.locked) {
            throw new IllegalStateException("Block palette is already locked!");
        }

        int legacyId = blockId << Block.DATA_BITS | data;
        this.legacyToRuntimeId.put(legacyId, runtimeId);
        if (replaceRuntimeMapping) {
            this.runtimeIdToLegacy.put(runtimeId, legacyId);
        } else {
            this.runtimeIdToLegacy.putIfAbsent(runtimeId, legacyId);
        }
    }

    /**
     * Replaces the sequential runtime ids by the network ids of the vanilla palette (the hash of each block state),
     * which the client uses when the start game packet says that block ids are hashed.
     *
     * @param networkIds the network id for every sequential runtime id
     */
    public void remapRuntimeIds(int[] networkIds) {
        if (this.locked) {
            throw new IllegalStateException("Block palette is already locked!");
        }

        this.sequentialToLegacy.putAll(this.runtimeIdToLegacy);
        this.sequentialToLegacy.defaultReturnValue(-1);

        Int2IntMap remappedLegacyToRuntime = new Int2IntOpenHashMap();
        remappedLegacyToRuntime.defaultReturnValue(-1);
        for (Int2IntMap.Entry entry : this.legacyToRuntimeId.int2IntEntrySet()) {
            int sequential = entry.getIntValue();
            remappedLegacyToRuntime.put(entry.getIntKey(), sequential >= 0 && sequential < networkIds.length ? networkIds[sequential] : sequential);
        }

        Int2IntMap remappedRuntimeToLegacy = new Int2IntOpenHashMap();
        remappedRuntimeToLegacy.defaultReturnValue(-1);
        for (Int2IntMap.Entry entry : this.runtimeIdToLegacy.int2IntEntrySet()) {
            int sequential = entry.getIntKey();
            remappedRuntimeToLegacy.put(sequential >= 0 && sequential < networkIds.length ? networkIds[sequential] : sequential, entry.getIntValue());
        }

        this.legacyToRuntimeId.clear();
        this.legacyToRuntimeId.putAll(remappedLegacyToRuntime);
        this.runtimeIdToLegacy.clear();
        this.runtimeIdToLegacy.putAll(remappedRuntimeToLegacy);
    }

    /**
     * @param sequentialId the position of the state in the sorted vanilla palette, as stored in the item json files
     */
    public int getLegacyFullIdBySequentialId(int sequentialId) {
        return this.sequentialToLegacy.isEmpty() ? this.getLegacyFullId(sequentialId) : this.sequentialToLegacy.get(sequentialId);
    }

    public void lock() {
        this.locked = true;
    }

    public int getRuntimeId(int id, int meta) {
        int legacyId = id << Block.DATA_BITS | meta;
        int runtimeId = this.legacyToRuntimeId.get(legacyId);
        if (runtimeId == -1) {
            runtimeId = legacyToRuntimeId.get(id << Block.DATA_BITS);
            if (runtimeId == -1) {
                runtimeId = legacyToRuntimeId.get(BlockID.INFO_UPDATE << Block.DATA_BITS);
                log.info("Missing block runtime id mappings for {}:{}", id, meta);
            }
        }
        return runtimeId;
    }

    public int getRuntimeId(int legacyId) {
        int runtimeId = this.legacyToRuntimeId.get(legacyId);
        if (runtimeId == -1) {
            log.info("Missing block runtime id mappings for {}", legacyId);
            return legacyToRuntimeId.get(BlockID.INFO_UPDATE << Block.DATA_BITS);
        }
        return runtimeId;
    }

    public int getLegacyFullId(int runtimeId) {
        return this.runtimeIdToLegacy.get(runtimeId);
    }

    public int getLegacyFullId(CompoundTag state) {
        return this.stateToLegacy.getOrDefault(state, -1);
    }

    /**
     * The vanilla state (name, states, version) of a block, as a copy; block entities that hold a block, like the flower pot and the
     * item frame, send it to the client in this form instead of the legacy numeric ids.
     *
     * @return the state, or null when the palette has none for this block and data
     */
    public CompoundTag getState(int blockId, int data) {
        CompoundTag state = this.legacyToState.get(blockId << Block.DATA_BITS | data);
        return state == null ? null : state.copy();
    }

    public Int2IntMap getLegacyToRuntimeIdMap() {
        return Int2IntMaps.unmodifiable(this.legacyToRuntimeId);
    }
}
