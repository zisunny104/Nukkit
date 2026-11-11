package cn.nukkit.blockentity;

import cn.nukkit.block.BlockGolemStatue;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * Holds the pose of a copper golem statue and the golem it turns back into, the layout follows the official server.
 */
public class BlockEntityCopperGolemStatue extends BlockEntity {

    public BlockEntityCopperGolemStatue(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    protected void initBlockEntity() {
        if (!this.namedTag.contains("Pose")) {
            this.namedTag.putInt("Pose", 0);
        }
        if (!this.namedTag.contains("Actor")) {
            this.namedTag.putCompound("Actor", new CompoundTag()
                    .putString("ActorIdentifier", "minecraft:copper_golem<>")
                    .putCompound("SaveData", new CompoundTag()));
        }
        super.initBlockEntity();
    }

    @Override
    public boolean isBlockEntityValid() {
        return this.getLevelBlock() instanceof BlockGolemStatue;
    }
}
