package cn.nukkit.test;

import cn.nukkit.level.BlockPalette;
import cn.nukkit.nbt.tag.CompoundTag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockPaletteStateTest {
    @Test
    void stateOfABlockIsReturnedAsACopy() {
        BlockPalette palette = new BlockPalette(0);
        CompoundTag state = new CompoundTag().putString("name", "minecraft:yellow_flower").putCompound("states", new CompoundTag()).putInt("version", 1);
        palette.registerState(37, 0, 5, state);

        CompoundTag found = palette.getState(37, 0);
        assertEquals("minecraft:yellow_flower", found.getString("name"));
        found.putString("name", "changed");
        assertEquals("minecraft:yellow_flower", palette.getState(37, 0).getString("name"));
    }

    @Test
    void unknownBlockHasNoState() {
        assertNull(new BlockPalette(0).getState(37, 0));
    }

    @Test
    void clearingTheStatesForgetsThem() {
        BlockPalette palette = new BlockPalette(0);
        palette.registerState(37, 0, 5, new CompoundTag().putString("name", "a"));
        palette.clearStates();
        assertNull(palette.getState(37, 0));
    }
}
