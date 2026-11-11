package cn.nukkit.block;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemBlock;
import cn.nukkit.item.ItemDye;
import cn.nukkit.item.ItemTool;
import cn.nukkit.level.particle.BoneMealParticle;
import cn.nukkit.utils.BlockColor;

import java.util.concurrent.ThreadLocalRandom;

public class BlockPaleMoss extends BlockDirt {

    public BlockPaleMoss() {
    }

    public BlockPaleMoss(int meta) {
        super(0);
    }

    @Override
    public int getId() {
        return PALE_MOSS_BLOCK;
    }

    @Override
    public String getName() {
        return "Pale Moss Block";
    }

    @Override
    public double getHardness() {
        return 0.1;
    }

    @Override
    public double getResistance() {
        return 2.5;
    }

    @Override
    public boolean onActivate(Item item, Player player) {
        if (item.getId() != Item.DYE || item.getDamage() != ItemDye.BONE_MEAL) {
            return false;
        }

        // Bone meal spreads a patch: nearby ground becomes pale moss, topped with carpet or grass.
        // TODO: pale hanging moss under ceilings, patch shape and odds taken from the official game.
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int dx = -3; dx <= 3; dx++) {
            for (int dz = -3; dz <= 3; dz++) {
                if (dx * dx + dz * dz > 10) {
                    continue;
                }
                for (int dy = 2; dy >= -2; dy--) {
                    Block ground = this.getLevel().getBlock(this.add(dx, dy, dz));
                    Block above = ground.up();
                    if (above.getId() != AIR) {
                        continue;
                    }
                    if (!isReplaceableGround(ground.getId())) {
                        break;
                    }
                    boolean center = dx == 0 && dz == 0 && dy == 0;
                    if (!center && random.nextInt(10) >= 8) {
                        break;
                    }
                    if (!center) {
                        this.getLevel().setBlock(ground, Block.get(PALE_MOSS_BLOCK), false, true);
                    }
                    float roll = random.nextFloat();
                    if (roll < 0.4f) {
                        this.getLevel().setBlock(above, Block.get(PALE_MOSS_CARPET), false, true);
                    } else if (roll < 0.55f) {
                        this.getLevel().setBlock(above, Block.get(TALL_GRASS), false, true);
                    }
                    break;
                }
            }
        }

        this.level.addParticle(new BoneMealParticle(this));

        if (player != null && !player.isCreative()) {
            item.count--;
        }
        return true;
    }

    private static boolean isReplaceableGround(int id) {
        return id == STONE || id == DIRT || id == GRASS || id == PODZOL || id == MYCELIUM || id == GRAVEL || id == DEEPSLATE
                || id == MOSS_BLOCK || id == PALE_MOSS_BLOCK;
    }

    @Override
    public BlockColor getColor() {
        return BlockColor.GREEN_BLOCK_COLOR;
    }

    @Override
    public boolean canSilkTouch() {
        return true;
    }

    @Override
    public int getFullId() {
        return this.getId() << Block.DATA_BITS;
    }

    @Override
    public void setDamage(int meta) {
        // Noop
    }

    @Override
    public Item toItem() {
        return new ItemBlock(Block.get(this.getId()), 0, 1);
    }

    @Override
    public Item[] getDrops(Item item) {
        if (this.canHarvestWithHand() || this.canHarvest(item)) {
            return new Item[]{this.toItem()};
        }
        return new Item[0];
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_HOE;
    }
}
