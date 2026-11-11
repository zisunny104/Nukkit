package cn.nukkit.level.generator.object.tree;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

/**
 * Pale oak: the 2x2 leaning trunk and roofed canopy of the official pale_oak_tree_feature
 * (height 6 + 0-2 + 0-1, same as dark oak), plus its side branches.
 */
public class ObjectPaleOakTree extends ObjectDarkOakTree {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public ObjectPaleOakTree() {
        super(Block.get(BlockID.PALE_OAK_LOG), Block.get(BlockID.PALE_OAK_LEAVES));
    }

    @Override
    protected void growBranch(ChunkManager level, NukkitRandom rand, int x, int y, int z) {
        if (rand.nextBoundedInt(3) != 0) {
            return;
        }
        int[] d = DIRS[rand.nextBoundedInt(4)];
        int len = 2 + rand.nextBoundedInt(4);
        int by = y - 1;
        int ex = x;
        int ez = z;
        for (int s = 1; s <= len; s++) {
            ex = x + d[0] * s;
            ez = z + d[1] * s;
            this.placeLogAt(level, new Vector3(ex, by + (s > 1 ? 1 : 0), ez));
        }
        int ey = by + (len > 1 ? 1 : 0) + 1;
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= 3) {
                    this.placeLeafAt(level, ex + dx, ey, ez + dz);
                }
            }
        }
    }
}
