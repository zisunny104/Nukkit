package cn.nukkit.test;

import cn.nukkit.block.*;
import cn.nukkit.item.Item;
import cn.nukkit.level.GlobalBlockPalette;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.leveldb.BlockStateMapping;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.Utils;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeavesFlagsTest {
    @Test @SuppressWarnings("unchecked")
    void flagsSurvivePaletteStorageAndPlacementForEveryLeafKind() {
        Block.init();
        if (GlobalBlockPalette.getCurrentBlockPalette().getLegacyToRuntimeIdMap().isEmpty()) {
            GlobalBlockPalette.init();
        }
        var states = (ListTag<CompoundTag>) Utils.loadTagResource("runtime_block_states.dat");
        int checked = 0;
        var species = new HashSet<String>();
        for (var state : states.getAll()) {
            int id = state.getInt("id"), data = state.getShort("data");
            Block block = Block.get(id, data);
            if (!(block instanceof BlockLeaves leaves)) continue;
            var flags = state.getCompound("states");
            if (!flags.contains("persistent_bit") || !flags.contains("update_bit")) continue;
            String label = state.getString("name") + ":" + data;
            assertEquals(flags.getBoolean("persistent_bit"), leaves.isPersistent(), label);
            assertEquals(flags.getBoolean("update_bit"), leaves.isCheckDecay(), label);
            var saved = BlockStateMapping.get().getState(id, data);
            var stored = saved.getVanillaState().getCompound("states");
            assertEquals(leaves.isPersistent(), stored.getBoolean("persistent_bit"), label);
            assertEquals(leaves.isCheckDecay(), stored.getBoolean("update_bit"), label);
            BlockLeaves loaded = (BlockLeaves) Block.get(saved.getLegacyId(), saved.getLegacyData());
            assertEquals(leaves.isPersistent(), loaded.isPersistent(), label);
            assertEquals(leaves.isCheckDecay(), loaded.isCheckDecay(), label);
            leaves.setPersistent(false);
            leaves.setCheckDecay(true);
            leaves.level = mock(Level.class);
            assertTrue(leaves.place(Item.get(Item.AIR), leaves, new BlockStone(), BlockFace.UP, 0.5, 1, 0.5, null));
            assertTrue(leaves.isPersistent(), label);
            assertEquals(0, leaves.onUpdate(Level.BLOCK_UPDATE_RANDOM), label);
            checked++;
            species.add(state.getString("name"));
        }
        assertTrue(checked >= 66);
        assertTrue(species.size() >= 14);
    }
}
