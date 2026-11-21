package cn.nukkit.inventory.transaction;

import cn.nukkit.Nukkit;
import cn.nukkit.Player;
import cn.nukkit.event.inventory.LoomItemEvent;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.inventory.LoomInventory;
import cn.nukkit.inventory.transaction.action.InventoryAction;
import cn.nukkit.inventory.transaction.action.LoomItemAction;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.item.ItemDye;
import cn.nukkit.item.RuntimeItems;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.BannerPattern;

import java.util.List;
import java.util.Map;

public class LoomTransaction extends InventoryTransaction {

    private static final Map<String, String> TEMPLATE_PATTERNS = Map.of(
            "cre", "creeper", "sku", "skull", "flo", "flower", "moj", "mojang",
            "bri", "field_masoned", "cbo", "bordure_indented", "pig", "piglin",
            "glb", "globe", "flw", "flow", "gus", "guster");

    private Item outputItem;

    public LoomTransaction(Player source, List<InventoryAction> actions) {
        super(source, actions);
    }

    @Override
    public void addAction(InventoryAction action) {
        if (action instanceof LoomItemAction) {
            if (this.outputItem != null) {
                this.invalid = true;
                source.getServer().getLogger().debug("Duplicate addAction for outputItem");
                return;
            }
            this.outputItem = action.getSourceItem();
        } else if (!(action instanceof SlotChangeAction)) {
            this.invalid = true;
            if (Nukkit.DEBUG > 1) {
                source.getServer().getLogger().debug(this.getClass().getSimpleName() + " unexpected addAction: " + action);
            }
            return;
        }
        super.addAction(action);
    }

    @Override
    public boolean canExecute() {
        if (!super.canExecute()) {
            return false;
        }

        Inventory inventory = getSource().getWindowById(Player.LOOM_WINDOW_ID);
        if (!(inventory instanceof LoomInventory)) {
            return false;
        }

        if (outputItem == null || outputItem.isNull()) {
            return false;
        }

        LoomInventory loomInventory = (LoomInventory) inventory;
        Item banner = loomInventory.getBanner();
        Item dye = loomInventory.getDye();
        if (!validPatternAddition(banner, dye, loomInventory.getPattern(), outputItem)) {
            return false;
        }

        CompoundTag a = banner.getNamedTag();
        a = a == null ? new CompoundTag() : a.clone().remove("Patterns");
        CompoundTag b = this.outputItem.getNamedTag();
        b = b == null ? new CompoundTag() : b.clone().remove("Patterns");
        if (!a.equals(b)) {
            if (Nukkit.DEBUG > 1) {
                source.getServer().getLogger().debug("NBT check failed: input=" + a + ", output=" + b);
            }
            return false;
        }

        return true;
    }

    static boolean validPatternAddition(Item banner, Item dye, Item pattern, Item output) {
        if (banner.getId() != Item.BANNER || output.getId() != Item.BANNER || output.getCount() != 1
                || !(dye instanceof ItemDye color) || dye.getCount() < 1 || banner.getDamage() != output.getDamage()
                || !output.hasCompoundTag()) return false;
        int meta = dye.getDamage();
        if (meta < 0 || meta > ItemDye.WHITE_NEW || meta == ItemDye.INK_SAC || meta == ItemDye.COCOA_BEANS
                || meta == ItemDye.LAPIS_LAZULI || meta == ItemDye.BONE_MEAL) return false;
        var oldPatterns = (banner.hasCompoundTag() ? banner.getNamedTag() : new CompoundTag()).getList("Patterns", CompoundTag.class);
        var newPatterns = output.getNamedTag().getList("Patterns", CompoundTag.class);
        CompoundTag originalData = banner.hasCompoundTag() ? banner.getNamedTag().clone() : new CompoundTag();
        if (!originalData.remove("Patterns").equals(output.getNamedTag().clone().remove("Patterns"))) return false;
        if (newPatterns.size() > 6 || newPatterns.size() != oldPatterns.size() + 1) return false;
        for (int i = 0; i < oldPatterns.size(); i++) {
            if (!oldPatterns.get(i).equals(newPatterns.get(i))) return false;
        }
        CompoundTag added = newPatterns.get(newPatterns.size() - 1);
        String code = added.getString("Pattern");
        if (BannerPattern.Type.getByName(code) == null || added.getInt("Color") != color.getDyeColor().getDyeData()) return false;
        String required = TEMPLATE_PATTERNS.get(code);
        if (required == null) return pattern.isNull();
        var mapping = RuntimeItems.getMapping().fromIdentifier("minecraft:" + required + "_banner_pattern");
        return mapping != null && pattern.getId() == mapping.getLegacyId() && pattern.getDamage() == mapping.getDamage();
    }

    @Override
    protected boolean callExecuteEvent() {
        LoomInventory inventory = (LoomInventory) getSource().getWindowById(Player.LOOM_WINDOW_ID);
        LoomItemEvent event = new LoomItemEvent(inventory, this.outputItem, this.source);
        this.source.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            this.sendInventories();
            source.setNeedSendInventory(true);
            return false;
        }
        return true;
    }

    public Item getOutputItem() {
        return this.outputItem;
    }

    public static boolean isIn(List<InventoryAction> actions) {
        for (InventoryAction action : actions) {
            if (action instanceof LoomItemAction) return true;
        }
        return false;
    }

    @Override
    public boolean checkForItemPart(List<InventoryAction> actions) {
        return isIn(actions);
    }
}
