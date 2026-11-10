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
        return super.overridable(id) || id == BlockID.POPLAR_LOG || id == BlockID.YELLOW_POPLAR_LEAVES;
    }

    @Override
    public int getTrunkBlock() {
        return BlockID.POPLAR_LOG;
    }

    @Override
    public int getLeafBlock() {
        return BlockID.YELLOW_POPLAR_LEAVES;
    }
}
