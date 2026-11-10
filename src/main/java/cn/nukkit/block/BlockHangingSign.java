package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;

/**
 * A sign that hangs from a ceiling or from a wall bracket.
 * Data is the index of the state in the official palette: facing_direction + 6 * ground_sign_direction + 96 * hanging + 192 * attached_bit.
 * TODO: the ceiling chain variants (attached_bit) and editing the text, only placing and the block entity exist so far.
 */
public abstract class BlockHangingSign extends BlockTransparentMeta {

    private static final int HANGING_OFFSET = 96;
    private static final int GROUND_DIRECTION_STEP = 6;

    protected BlockHangingSign(int meta) {
        super(meta);
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        if (face == BlockFace.UP) {
            return false;
        }

        if (face == BlockFace.DOWN) {
            int direction = player != null ? (int) Math.floor((player.yaw + 180) * 16 / 360 + 0.5) & 0x0f : 0;
            this.setDamage(HANGING_OFFSET + direction * GROUND_DIRECTION_STEP);
        } else {
            // the captured wall placement on a north face has facing_direction 4, the sign runs along the wall
            this.setDamage(face.getAxis() == BlockFace.Axis.Z ? 4 : 2);
        }

        if (!this.getLevel().setBlock(block, this, true, true)) {
            return false;
        }

        CompoundTag nbt = new CompoundTag()
                .putString("id", BlockEntity.HANGING_SIGN)
                .putInt("x", (int) block.x)
                .putInt("y", (int) block.y)
                .putInt("z", (int) block.z);
        if (player != null) {
            nbt.putString("Creator", player.getUniqueId().toString());
        }
        BlockEntity.createBlockEntity(BlockEntity.HANGING_SIGN, this.getChunk(), nbt);
        return true;
    }

    @Override
    public double getHardness() {
        return 1;
    }

    @Override
    public double getResistance() {
        return 5;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }
}
