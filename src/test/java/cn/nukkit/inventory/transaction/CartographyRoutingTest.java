package cn.nukkit.inventory.transaction;

import cn.nukkit.Player;
import cn.nukkit.inventory.*;
import cn.nukkit.item.*;
import cn.nukkit.level.Position;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.types.ContainerIds;
import cn.nukkit.network.protocol.types.NetworkInventoryAction;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartographyRoutingTest {
    @Test void inputMaterialAndOutputUseTheirOwnBedrockSlotsAndCloseReturnsOnlyInputs() {
        cn.nukkit.block.Block.init();
        PlayerUIInventory ui = mock(PlayerUIInventory.class);
        Item input = new ItemMap();
        Item material = Item.get(Item.EMPTY_MAP);
        when(ui.getItem(12)).thenReturn(input);
        when(ui.getItem(13)).thenReturn(material);
        when(ui.getItem(50)).thenReturn(input);
        CartographyInventory inventory = new CartographyInventory(ui, new Position(0, 64, 0));
        assertSame(input, inventory.getInputSlot());
        assertSame(material, inventory.getMaterialSlot());
        assertSame(input, inventory.getOutputSlot());
        assertEquals(Map.of(0, input, 1, material), inventory.getContents());
        Player player = mock(Player.class, RETURNS_DEEP_STUBS);
        when(player.getWindowById(Player.ANVIL_WINDOW_ID)).thenReturn(inventory);
        for (int slot : new int[]{12, 13, 50}) {
            NetworkInventoryAction network = new NetworkInventoryAction();
            network.sourceType = NetworkInventoryAction.SOURCE_CONTAINER;
            network.windowId = ContainerIds.UI;
            network.inventorySlot = slot;
            network.oldItem = Item.get(Item.AIR);
            network.newItem = input;
            var action = network.createInventoryAction(player);
            assertInstanceOf(SlotChangeAction.class, action);
            assertSame(inventory, ((SlotChangeAction)action).getInventory());
            assertEquals(slot == 50 ? 2 : slot - 12, ((SlotChangeAction)action).getSlot());
        }
    }

    @Test void successfulScalingWritesAnAuthoritativeIdentityAndResendsTheOutputSlot() throws Exception {
        cn.nukkit.block.Block.init();
        Player player = mock(Player.class, RETURNS_DEEP_STUBS);
        var level = mock(cn.nukkit.level.Level.class);
        player.level = level;
        when(level.getBlock(anyInt(), anyInt(), anyInt())).thenReturn(new cn.nukkit.block.BlockCartographyTable());
        when(player.isCreative()).thenReturn(true);
        CartographyInventory inventory = mock(CartographyInventory.class);
        when(player.getWindowById(Player.ANVIL_WINDOW_ID)).thenReturn(inventory);
        when(inventory.getHolder()).thenReturn(new FakeBlockMenu(inventory, new Position(0, 64, 0, level)));
        ItemMap original = new ItemMap();
        original.setNamedTag(new CompoundTag().putLong("map_uuid", 12).putByteArray("Colors", new byte[]{1}));
        original.setMapOrigin(64, 64, 0);
        Item paper = Item.get(Item.PAPER);
        ItemMap clientOutput = (ItemMap) original.clone();
        when(inventory.getInputSlot()).thenReturn(original);
        when(inventory.getMaterialSlot()).thenReturn(paper);
        when(inventory.getOutputSlot()).thenReturn(Item.get(Item.AIR));
        RepairItemTransaction transaction = spy(new RepairItemTransaction(player, List.of()));
        doReturn(true).when(transaction).matchItems();
        for (var entry : Map.of("inputItem", original, "materialItem", paper, "outputItem", clientOutput.clone()).entrySet()) {
            var field = RepairItemTransaction.class.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(transaction, entry.getValue());
        }
        var outputInventory = mock(cn.nukkit.inventory.PlayerInventory.class);
        SlotChangeAction action = mock(SlotChangeAction.class);
        when(action.getInventory()).thenReturn(outputInventory);
        when(action.getSlot()).thenReturn(4);
        when(action.getTargetItemUnsafe()).thenReturn(clientOutput);
        when(action.onPreExecute(player)).thenReturn(true);
        when(action.execute(player)).thenReturn(true);
        transaction.getActionList().add(action);
        assertTrue(transaction.execute());
        assertNotEquals(12, clientOutput.getMapId());
        assertEquals(1, clientOutput.getMapScale());
        assertFalse(clientOutput.getNamedTag().contains("Colors"));
        assertEquals(12, original.getMapId());
        assertEquals(0, original.getMapScale());
        verify(outputInventory).sendSlot(4, player);
    }

    @Test void mapPacketSendsStoredScaleAndLockStateAndImageSurvivesIdentityInitialization() {
        ItemMap map = new ItemMap();
        map.setNamedTag(new CompoundTag().putInt("map_scale", 2).putBoolean("map_locked", true));
        map.setMapOrigin(64, -64, 0);
        map.initItem();
        assertTrue(map.getMapId() > 0);
        assertTrue(map.hasMapOrigin());
        assertEquals(2, map.getMapScale());
        assertTrue(map.isMapLocked());
        assertEquals(64, map.getMaxStackSize());
        map.setImage(new java.awt.image.BufferedImage(128, 128, java.awt.image.BufferedImage.TYPE_INT_RGB));
        Player player = mock(Player.class);
        assertTrue(map.trySendImage(player));
        var packet = org.mockito.ArgumentCaptor.forClass(cn.nukkit.network.protocol.ClientboundMapItemDataPacket.class);
        verify(player).dataPacket(packet.capture());
        assertEquals(2, packet.getValue().scale);
        assertTrue(packet.getValue().isLocked);
        assertEquals(64, packet.getValue().origin.x);
        assertEquals(-64, packet.getValue().origin.z);
    }
}
