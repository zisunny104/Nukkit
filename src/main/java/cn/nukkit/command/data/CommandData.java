package cn.nukkit.command.data;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class CommandData implements Cloneable {

    public CommandEnum aliases = null;
    public String description = "description";
    public Map<String, CommandOverload> overloads = new HashMap<>();

    public int flags;
    public int permission;

    @Override
    public CommandData clone() {
        try {
            CommandData copy = (CommandData) super.clone();
            // Each command packet must keep its own overloads while it is encoded.
            copy.overloads = new HashMap<>(this.overloads);
            return copy;
        } catch (Exception e) {
            return new CommandData();
        }
    }
}
