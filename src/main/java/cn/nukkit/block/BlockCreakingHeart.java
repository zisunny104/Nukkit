package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;

/**
 * Data is the index of the state in the official palette: pillar_axis (y, x, z) + 3 * creaking_heart_state (uprooted, dormant, awake) + 9 * natural.
 * It is dormant when it sits between two pale oak logs along its axis, otherwise uprooted.
 * TODO: the awake state, the creaking and the resin.
 */
public class BlockCreakingHeart extends BlockSolidMeta {

    private static final int AXIS_STATES = 3;
    private static final int STATE_UPROOTED = 0;
    private static final int STATE_DORMANT = 1;

    public BlockCreakingHeart() {
        this(0);
    }

    public BlockCreakingHeart(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return CREAKING_HEART;
    }

    @Override
    public String getName() {
        return "Creaking Heart";
    }

    @Override
    public double getHardness() {
        return 10;
    }

    @Override
    public double getResistance() {
        return 10;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        int axis = face.getAxis() == BlockFace.Axis.Y ? 0 : face.getAxis() == BlockFace.Axis.X ? 1 : 2;
        this.setDamage(this.withState(axis, this.supported(axis) ? STATE_DORMANT : STATE_UPROOTED));
        return this.getLevel().setBlock(block, this, true, true);
    }

    @Override
    public int onUpdate(int type) {
        if (type == Level.BLOCK_UPDATE_NORMAL) {
            int damage = this.getDamage();
            int axis = damage % AXIS_STATES;
            int state = (damage / AXIS_STATES) % 3;
            if (state != 2) { // an awake heart keeps its state
                int wanted = this.supported(axis) ? STATE_DORMANT : STATE_UPROOTED;
                if (wanted != state) {
                    this.setDamage(this.withState(axis, wanted));
                    this.getLevel().setBlock(this, this, true, false);
                }
            }
            return type;
        }
        return 0;
    }

    private int withState(int axis, int state) {
        int natural = this.getDamage() / (AXIS_STATES * 3);
        return axis + AXIS_STATES * state + AXIS_STATES * 3 * natural;
    }

    private boolean supported(int axis) {
        BlockFace positive = axis == 0 ? BlockFace.UP : axis == 1 ? BlockFace.EAST : BlockFace.SOUTH;
        return this.getSide(positive).getId() == PALE_OAK_LOG && this.getSide(positive.getOpposite()).getId() == PALE_OAK_LOG;
    }
}
