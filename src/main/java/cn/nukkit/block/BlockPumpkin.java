package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemID;
import cn.nukkit.item.ItemTool;
import cn.nukkit.math.BlockFace;
import cn.nukkit.utils.BlockColor;
import cn.nukkit.utils.Faceable;

/**
 * Created on 2015/12/8 by xtypr.
 * Package cn.nukkit.block in project Nukkit .
 */
public class BlockPumpkin extends BlockSolidMeta implements Faceable {

    public BlockPumpkin() {
        this(0);
    }

    public BlockPumpkin(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pumpkin";
    }

    @Override
    public int getId() {
        return PUMPKIN;
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
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId(), 0), 0);
    }

    @Override
    public boolean place(Item item, Block block, Block target, BlockFace face, double fx, double fy, double fz, Player player) {
        this.setBlockFace(player != null ? player.getDirection().getOpposite() : BlockFace.SOUTH);
        this.getLevel().setBlock(block, this, true, true);
        if (this.getId() == CARVED_PUMPKIN || this.getId() == LIT_PUMPKIN) {
            this.trySpawnGolem(player);
        }
        return true;
    }

    /**
     * Build a golem when the head is placed: snow golem (2 snow blocks), iron golem (T of 4 iron blocks),
     * copper golem (1 copper block; carved pumpkin only).
     */
    protected void trySpawnGolem(Player player) {
        this.golemYaw = player != null ? player.getYaw() : 0;
        int x = this.getFloorX();
        int y = this.getFloorY();
        int z = this.getFloorZ();
        cn.nukkit.level.Level level = this.getLevel();

        if (level.getBlockIdAt(x, y - 1, z) == SNOW_BLOCK && level.getBlockIdAt(x, y - 2, z) == SNOW_BLOCK) {
            this.clearGolemBlocks(x, y, z, 0, -1, 0, 0, -2, 0);
            this.spawnGolem("SnowGolem", x + 0.5, y - 2, z + 0.5);
            return;
        }

        if (level.getBlockIdAt(x, y - 1, z) == IRON_BLOCK && level.getBlockIdAt(x, y - 2, z) == IRON_BLOCK
                && level.getBlockIdAt(x, y - 3, z) == IRON_BLOCK) {
            if (level.getBlockIdAt(x - 1, y - 2, z) == IRON_BLOCK && level.getBlockIdAt(x + 1, y - 2, z) == IRON_BLOCK) {
                this.clearGolemBlocks(x, y, z, 0, -1, 0, 0, -2, 0, 0, -3, 0, -1, -2, 0, 1, -2, 0);
                this.spawnGolem("IronGolem", x + 0.5, y - 3, z + 0.5);
                return;
            }
            if (level.getBlockIdAt(x, y - 2, z - 1) == IRON_BLOCK && level.getBlockIdAt(x, y - 2, z + 1) == IRON_BLOCK) {
                this.clearGolemBlocks(x, y, z, 0, -1, 0, 0, -2, 0, 0, -3, 0, 0, -2, -1, 0, -2, 1);
                this.spawnGolem("IronGolem", x + 0.5, y - 3, z + 0.5);
                return;
            }
        }

        if (this.getId() == CARVED_PUMPKIN && isCopperBlock(level.getBlockIdAt(x, y - 1, z))) {
            this.clearGolemBlocks(x, y, z, 0, -1, 0);
            this.spawnGolem("CopperGolem", x + 0.5, y - 1, z + 0.5);
        }
    }

    private double golemYaw;

    private static boolean isCopperBlock(int id) {
        return id >= COPPER_BLOCK && id <= WAXED_WEATHERED_COPPER || id == WAXED_OXIDIZED_COPPER;
    }

    private void clearGolemBlocks(int x, int y, int z, int... offsets) {
        cn.nukkit.level.Level level = this.getLevel();
        level.setBlock(new cn.nukkit.math.Vector3(x, y, z), Block.get(AIR), true, false);
        for (int i = 0; i + 2 < offsets.length; i += 3) {
            level.setBlock(new cn.nukkit.math.Vector3(x + offsets[i], y + offsets[i + 1], z + offsets[i + 2]), Block.get(AIR), true, false);
        }
    }

    private void spawnGolem(String name, double x, double y, double z) {
        cn.nukkit.entity.Entity golem = cn.nukkit.entity.Entity.createEntity(name, new cn.nukkit.level.Position(x, y, z, this.getLevel()));
        if (golem != null) {
            golem.setRotation(this.golemYaw, 0);
            golem.spawnToAll();
        }
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.ORANGE_BLOCK_COLOR;
    }

    @Override
    public BlockFace getBlockFace() {
        return BlockFace.fromHorizontalIndex(this.getDamage() & 0x7);
    }

    @Override
    public boolean breakWhenPushed() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }


    @Override
    public boolean onActivate(Item item, Player player) {
        if (!item.isShears()) {
            return false;
        }

        BlockPumpkinCarved carvedPumpkin = new BlockPumpkinCarved();
        carvedPumpkin.setBlockFace(this.getBlockFace());
        item.useOn(this);
        this.level.setBlock(this, carvedPumpkin, true, true);
        this.getLevel().dropItem(add(0.5, 0.5, 0.5), Item.get(ItemID.PUMPKIN_SEEDS));
        this.getLevel().dropItem(add(0.5, 0.5, 0.5), Item.get(Item.PUMPKIN_SEEDS));return true;
    }

    public void setBlockFace(BlockFace blockFace) {
        this.setDamage(blockFace.getHorizontalIndex());
    }
}
