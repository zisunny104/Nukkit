package cn.nukkit.item;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;

public class ItemEmptyMap extends Item {

    public ItemEmptyMap() {
        this(0, 1);
    }

    public ItemEmptyMap(Integer meta) {
        this(meta, 1);
    }

    public ItemEmptyMap(Integer meta, int count) {
        super(EMPTY_MAP, meta, count, "Empty Map");
    }

    private ItemMap createMap(Player player) {
        ItemMap map = (ItemMap) Item.get(Item.MAP, getDamage());
        map.setMapOrigin(Math.floorDiv(player.getFloorX(), 128) * 128 + 64,
                Math.floorDiv(player.getFloorZ(), 128) * 128 + 64, player.getLevel().getDimension());
        map.initItem();
        return map;
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if (!player.isCreative()) {
            this.count--;
        }
        player.getInventory().addItem(createMap(player));
        return true;
    }

    @Override
    public boolean onClickAir(Player player, Vector3 directionVector) {
        if (!player.isCreative()) {
            this.count--;
        }
        player.getInventory().addItem(createMap(player));
        return true;
    }
}
