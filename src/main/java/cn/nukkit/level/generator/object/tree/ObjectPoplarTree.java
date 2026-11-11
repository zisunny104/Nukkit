package cn.nukkit.level.generator.object.tree;

import cn.nukkit.block.BlockID;

/** Oak shape with poplar log and leaves. TODO: official poplar shape and the other leaf colors. */
public class ObjectPoplarTree extends ObjectOakTree {

    @Override
    public int getType() {
        return 0;
    }

    @Override
    protected boolean overridable(int id) {
        return super.overridable(id) || id == BlockID.POPLAR_LOG || (id >= BlockID.YELLOW_POPLAR_LEAVES && id <= BlockID.RED_POPLAR_LEAVES);
    }

    @Override
    public int getTrunkBlock() {
        return BlockID.POPLAR_LOG;
    }

    /** One of the three leaf colors, chosen once per tree. */
    private int leaf = BlockID.YELLOW_POPLAR_LEAVES;

    @Override
    public void placeObject(cn.nukkit.level.ChunkManager level, int x, int y, int z, cn.nukkit.math.NukkitRandom random) {
        this.leaf = BlockID.YELLOW_POPLAR_LEAVES + random.nextBoundedInt(3);
        super.placeObject(level, x, y, z, random);
    }

    @Override
    public int getLeafBlock() {
        return this.leaf;
    }
}
