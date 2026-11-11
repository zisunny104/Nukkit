package cn.nukkit.level.generator.object.tree;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

/**
 * Poplar tree after the official poplar_trunk / poplar_canopy feature data:
 * trunk 7-11, 1-4 foliage support branches, a wide rounded canopy (radius 5-8, height 5-6) with 15% side holes,
 * one leaf color (yellow, orange or red) per tree.
 */
public class ObjectPoplarTree extends TreeGenerator {

    private static final int[] RADIUS = {5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 8};
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private final Block log = Block.get(BlockID.POPLAR_LOG);
    private final int leafId;

    public ObjectPoplarTree() {
        this(-1);
    }

    /** @param leafId poplar leaves block id, or -1 for a random color */
    public ObjectPoplarTree(int leafId) {
        this.leafId = leafId;
    }

    @Override
    public boolean generate(ChunkManager level, NukkitRandom rand, Vector3 position) {
        int x = position.getFloorX();
        int y = position.getFloorY();
        int z = position.getFloorZ();
        Block leaves = Block.get(this.leafId >= 0 ? this.leafId : BlockID.YELLOW_POPLAR_LEAVES + rand.nextBoundedInt(3));

        int height = 7 + rand.nextBoundedInt(5);
        int radius = RADIUS[rand.nextBoundedInt(RADIUS.length)];
        int canopyH = 5 + rand.nextBoundedInt(2);
        int branchY = height - 4;
        if (y < 1 || y + height + canopyH >= level.getMaxBlockY()) {
            return false;
        }
        int below = level.getBlockIdAt(x, y - 1, z);
        if (below != Block.GRASS && below != Block.DIRT) {
            return false;
        }
        for (int dy = 0; dy < height + canopyH; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (!this.overridable(level.getBlockIdAt(x + dx, y + dy, z + dz))) {
                        return false;
                    }
                }
            }
        }
        this.setDirtAt(level, new Vector3(x, y - 1, z));

        // canopy: an ellipsoid centred on the top of the trunk, with random holes on the rim
        int cy = y + branchY + canopyH / 2;
        for (int dy = 0; dy < canopyH; dy++) {
            double t = (dy + 0.5 - canopyH / 2.0) / (canopyH / 2.0);
            int r = (int) Math.round(radius * Math.sqrt(Math.max(0, 1 - t * t * 0.8)));
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    double d = Math.sqrt(dx * dx + dz * dz);
                    if (d > r + 0.3) {
                        continue;
                    }
                    if (d > r - 1 && rand.nextFloat() < 0.15f) {
                        continue;
                    }
                    this.leaf(level, x + dx, y + branchY + dy, z + dz, leaves);
                }
            }
        }

        // trunk
        for (int dy = 0; dy < height; dy++) {
            this.set(level, x, y + dy, z, log);
        }
        // foliage support branches, each in its own direction
        int branches = 1 + rand.nextBoundedInt(4);
        int start = rand.nextBoundedInt(4);
        for (int i = 0; i < branches; i++) {
            int[] d = DIRS[(start + i) % 4];
            int len = Math.max(2, radius - 2 - rand.nextBoundedInt(2));
            for (int s = 1; s <= len; s++) {
                this.set(level, x + d[0] * s, y + branchY + (s > 2 ? 1 : 0), z + d[1] * s, log);
            }
        }
        return true;
    }

    protected boolean overridable(int id) {
        return this.canGrowInto(id) || id == BlockID.POPLAR_LOG || (id >= BlockID.YELLOW_POPLAR_LEAVES && id <= BlockID.RED_POPLAR_LEAVES) || id == BlockID.SNOW_LAYER;
    }

    private void set(ChunkManager level, int x, int y, int z, Block b) {
        if (this.overridable(level.getBlockIdAt(x, y, z))) {
            level.setBlockAt(x, y, z, b.getId(), b.getDamage());
        }
    }

    private void leaf(ChunkManager level, int x, int y, int z, Block b) {
        int id = level.getBlockIdAt(x, y, z);
        if (id == Block.AIR || id == BlockID.SNOW_LAYER) {
            level.setBlockAt(x, y, z, b.getId(), b.getDamage());
        }
    }
}
