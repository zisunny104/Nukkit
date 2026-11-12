package cn.nukkit.utils;

import cn.nukkit.item.Item;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Minimal reader for the vanilla loot table format: pools, rolls, weighted item or nested table entries,
 * random_chance conditions and the set_count function. Other functions are ignored.
 */
public class SimpleLootTable {

    private static final Map<String, JsonObject> CACHE = new ConcurrentHashMap<>();

    private SimpleLootTable() {
    }

    /**
     * @param path resource path below loot_tables/, for example {@code chests/trial_chambers/reward}
     */
    public static List<Item> roll(String path, Random random) {
        List<Item> result = new ArrayList<>();
        roll(path, random, result, 0);
        return result;
    }

    private static void roll(String path, Random random, List<Item> out, int depth) {
        JsonObject table = load(path);
        if (table == null || depth > 8 || !table.has("pools")) {
            return;
        }

        for (JsonElement poolElement : table.getAsJsonArray("pools")) {
            JsonObject pool = poolElement.getAsJsonObject();
            if (!conditionsPass(pool, random)) {
                continue;
            }

            int rolls = number(pool.get("rolls"), random);
            JsonArray entries = pool.has("entries") ? pool.getAsJsonArray("entries") : new JsonArray();
            for (int i = 0; i < rolls; i++) {
                JsonObject entry = pick(entries, random);
                if (entry == null) {
                    continue;
                }
                String type = entry.has("type") ? entry.get("type").getAsString() : "";
                if (type.equals("loot_table")) {
                    String name = entry.get("name").getAsString().replace("loot_tables/", "");
                    roll(name.endsWith(".json") ? name.substring(0, name.length() - 5) : name, random, out, depth + 1);
                } else if (type.equals("item")) {
                    Item item = Item.fromString(entry.get("name").getAsString());
                    if (item == null || item.isNull()) {
                        continue;
                    }
                    int count = 1;
                    if (entry.has("functions")) {
                        for (JsonElement fn : entry.getAsJsonArray("functions")) {
                            JsonObject f = fn.getAsJsonObject();
                            if (f.has("function") && f.get("function").getAsString().equals("set_count")) {
                                count = number(f.get("count"), random);
                            }
                        }
                    }
                    item.setCount(Math.max(1, count));
                    out.add(item);
                }
            }
        }
    }

    private static boolean conditionsPass(JsonObject pool, Random random) {
        if (!pool.has("conditions")) {
            return true;
        }
        for (JsonElement c : pool.getAsJsonArray("conditions")) {
            JsonObject cond = c.getAsJsonObject();
            if (cond.has("condition") && cond.get("condition").getAsString().equals("random_chance")
                    && random.nextDouble() >= cond.get("chance").getAsDouble()) {
                return false;
            }
        }
        return true;
    }

    private static JsonObject pick(JsonArray entries, Random random) {
        int total = 0;
        for (JsonElement e : entries) {
            total += weight(e.getAsJsonObject());
        }
        if (total <= 0) {
            return null;
        }
        int r = random.nextInt(total);
        for (JsonElement e : entries) {
            r -= weight(e.getAsJsonObject());
            if (r < 0) {
                return e.getAsJsonObject();
            }
        }
        return null;
    }

    private static int weight(JsonObject entry) {
        return entry.has("weight") ? entry.get("weight").getAsInt() : 1;
    }

    private static int number(JsonElement element, Random random) {
        if (element == null) {
            return 1;
        }
        if (element.isJsonObject()) {
            JsonObject o = element.getAsJsonObject();
            int min = (int) o.get("min").getAsDouble();
            int max = (int) o.get("max").getAsDouble();
            return max <= min ? min : min + random.nextInt(max - min + 1);
        }
        return (int) element.getAsDouble();
    }

    private static JsonObject load(String path) {
        return CACHE.computeIfAbsent(path, p -> {
            try (InputStream in = SimpleLootTable.class.getClassLoader().getResourceAsStream("loot_tables/" + p + ".json")) {
                if (in == null) {
                    return new JsonObject();
                }
                return JsonParser.parseReader(new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
            } catch (Exception e) {
                return new JsonObject();
            }
        });
    }
}
