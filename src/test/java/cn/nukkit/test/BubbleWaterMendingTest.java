package cn.nukkit.test;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.*;
import cn.nukkit.entity.item.EntityXPOrb;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.inventory.PlayerOffhandInventory;
import cn.nukkit.item.Item;
import cn.nukkit.item.RuntimeItems;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.level.Level;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.Vector3;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BubbleWaterMendingTest {
    @BeforeAll static void initialize() {
        Block.init();
        if (RuntimeItems.getMapping() == null) RuntimeItems.init();
        Item.init();
        Enchantment.init();
    }

    @Test void bubbleColumnRepairsMissingWaterLayer() {
        Level level = mock(Level.class);
        BlockBubbleColumn column = new BlockBubbleColumn();
        column.level = level;
        column.y = 64;
        when(level.getBlock(any(Vector3.class), any(BlockLayer.class), eq(true))).thenAnswer(call -> {
            Vector3 point = call.getArgument(0);
            return point.getFloorY() == 63 ? new BlockSoulSand() : new BlockAir();
        });
        column.onUpdate(Level.BLOCK_UPDATE_NORMAL);
        verify(level).setBlock(eq(column), eq(Block.LAYER_WATERLOGGED),
                argThat(block -> block.getId() == Block.STILL_WATER), eq(true), eq(false));
    }

    @Test void existingWaterLayerIsNotWrittenAgain() {
        Level level = mock(Level.class);
        BlockBubbleColumn column = new BlockBubbleColumn();
        column.level = level;
        column.y = 64;
        when(level.getBlock(any(Vector3.class), any(BlockLayer.class), eq(true))).thenAnswer(call -> {
            Vector3 point = call.getArgument(0);
            BlockLayer layer = call.getArgument(1);
            if (layer == Block.LAYER_WATERLOGGED) return new BlockWater();
            return point.getFloorY() == 63 ? new BlockSoulSand() : new BlockAir();
        });
        column.onUpdate(Level.BLOCK_UPDATE_NORMAL);
        verify(level, never()).setBlock(any(Vector3.class), eq(Block.LAYER_WATERLOGGED),
                any(Block.class), anyBoolean(), anyBoolean());
    }

    @Test void experiencePickupReturnsUnusedXpAndRoundsOddRepairsUp() throws Exception {
        for (int[] sample : new int[][]{{2, 10, 9}, {3, 10, 8}, {100, 10, 0}}) {
            Player player = mock(Player.class);
            Server server = mock(Server.class);
            Level level = mock(Level.class);
            PlayerInventory inventory = mock(PlayerInventory.class);
            PlayerOffhandInventory offhand = mock(PlayerOffhandInventory.class);
            AxisAlignedBB bounds = mock(AxisAlignedBB.class);
            EntityXPOrb orb = mock(EntityXPOrb.class);
            Item tool = Item.get(Item.DIAMOND_PICKAXE, sample[0]);
            tool.addEnchantment(Enchantment.getEnchantment(Enchantment.ID_MENDING));
            assign(player, "spawned", true);
            assign(player, "server", server);
            assign(player, "inventory", inventory);
            assign(player, "boundingBox", bounds);
            when(player.isAlive()).thenReturn(true);
            when(player.isOnline()).thenReturn(true);
            when(player.getLevel()).thenReturn(level);
            when(player.getOffhandInventory()).thenReturn(offhand);
            when(server.getTick()).thenReturn(1);
            when(bounds.isVectorInside(orb)).thenReturn(true);
            when(inventory.getItemInHandFast()).thenReturn(tool);
            when(inventory.getItem(0)).thenReturn(tool);
            when(inventory.getArmorItem(anyInt())).thenReturn(Item.get(Item.AIR));
            when(offhand.getItem(0)).thenReturn(Item.get(Item.AIR));
            when(orb.getExp()).thenReturn(sample[1]);
            doCallRealMethod().when(player).pickupEntity(orb, false);
            assertTrue(player.pickupEntity(orb, false));
            verify(player).addExperience(sample[2]);
            verify(inventory).setItem(eq(0),
                    argThat(item -> item.getDamage() == Math.max(0, sample[0] - sample[1] * 2)));
            verify(orb).close();
        }
    }

    private static void assign(Object object, String name, Object value) throws Exception {
        for (Class<?> type = Player.class; type != null; type = type.getSuperclass()) {
            try {
                var field = type.getDeclaredField(name);
                field.setAccessible(true);
                field.set(object, value);
                return;
            } catch (NoSuchFieldException missing) {
                // Continue through the entity hierarchy.
            }
        }
        throw new NoSuchFieldException(name);
    }
}
