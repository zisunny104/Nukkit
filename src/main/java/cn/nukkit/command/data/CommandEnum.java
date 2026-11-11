package cn.nukkit.command.data;

import cn.nukkit.block.BlockID;
import cn.nukkit.item.ItemID;
import cn.nukkit.potion.Effect;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author CreeperFace
 */
@ToString
@EqualsAndHashCode
public class CommandEnum {

    public static final CommandEnum ENUM_BOOLEAN = new CommandEnum("Boolean", ImmutableList.of("true", "false"));
    public static final CommandEnum ENUM_GAMEMODE = new CommandEnum("GameMode", ImmutableList.of("default", "creative", "spectator", "survival", "adventure", "d", "c", "s", "a"));
    public static final CommandEnum ENUM_DIFFICULTY = new CommandEnum("Difficulty", ImmutableList.of("peaceful", "p", "easy", "e", "normal", "n", "hard", "h"));
    public static final CommandEnum ENUM_BLOCK;
    public static final CommandEnum ENUM_ITEM;
    public static final CommandEnum ENUM_EFFECTS;

    static {
        // Official names from runtime_item_states.json (negative runtime id = block item), plus the old field names as aliases
        java.util.LinkedHashSet<String> blockNames = new java.util.LinkedHashSet<>();
        java.util.LinkedHashSet<String> itemNames = new java.util.LinkedHashSet<>();
        for (Field field : BlockID.class.getDeclaredFields()) {
            blockNames.add(field.getName().toLowerCase(Locale.ROOT));
        }
        for (Field field : ItemID.class.getDeclaredFields()) {
            itemNames.add(field.getName().toLowerCase(Locale.ROOT));
        }
        itemNames.addAll(blockNames);
        try {
            for (com.google.gson.JsonElement element : cn.nukkit.utils.Utils.loadJsonResource("runtime_item_states.json").getAsJsonArray()) {
                com.google.gson.JsonObject entry = element.getAsJsonObject();
                String name = entry.get("name").getAsString();
                if (name.startsWith("minecraft:")) {
                    name = name.substring("minecraft:".length());
                }
                itemNames.add(name);
                if (entry.get("id").getAsInt() < 0) {
                    blockNames.add(name);
                }
            }
        } catch (Exception ignore) {
            // keep the field name aliases only
        }
        ENUM_BLOCK = new CommandEnum("Block", ImmutableList.copyOf(blockNames));
        ENUM_ITEM = new CommandEnum("Item", ImmutableList.copyOf(itemNames));

        ImmutableList.Builder<String> effects = ImmutableList.builder();
        for (Field field : Effect.class.getDeclaredFields()) {
            if (field.getType() == int.class && field.getModifiers() == (Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL)) {
                effects.add(field.getName().toLowerCase(Locale.ROOT));
            }
        }
        ENUM_EFFECTS = new CommandEnum("Effect", effects.build());
    }

    private final String name;
    private final List<String> values;

    public CommandEnum(String name, String... values) {
        this(name, Arrays.asList(values));
    }

    public CommandEnum(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }
}
