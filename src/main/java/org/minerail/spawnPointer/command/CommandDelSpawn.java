package org.minerail.spawnPointer.command;

import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.minerail.spawnPointer.SpawnPointer;
import org.minerail.spawnPointer.util.MessageDeliverUtil;

import java.util.List;

public class CommandDelSpawn {
    private SpawnPointer plugin;
    public CommandDelSpawn(SpawnPointer plugin) {
        this.plugin = plugin;
    }

    public void register(Commands cmds) {
        cmds.register(plugin.getPluginMeta(),
                Commands.literal("delspawn").executes(ctx -> execute(ctx.getSource().getSender()))
                        .requires(ctx -> ctx.getSender().hasPermission("spawn.use.admin")).build(), null, List.of()
                );
    }

    public int execute(CommandSender sender) {
        Player p = (Player) sender;
        if (plugin.getDataFile().get().get("spawn") != null) {
            plugin.getDataFile().get().set("spawn", null);
            MessageDeliverUtil.sendFormatted(plugin.getConfigFile().get().getString("message.del-spawn-success"),
                    p, plugin.getConfigFile().get().getString("color-format"));
            plugin.getDataFile().save();
            return 1;
        }
        MessageDeliverUtil.sendFormatted(plugin.getConfigFile().get().getString("message.del-spawn-failure"),
                p, plugin.getConfigFile().get().getString("color-format"));
        return 1;
    }
}
