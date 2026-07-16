package org.minerail.spawnPointer.command;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.math.FinePosition;
import io.papermc.paper.math.Position;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.minerail.spawnPointer.SpawnPointer;
import org.minerail.spawnPointer.util.MessageDeliverUtil;

import java.util.List;

public class CommandSpawn {
    private SpawnPointer plugin;
    public CommandSpawn(SpawnPointer plugin) {
        this.plugin = plugin;
    }

    public void register(Commands cmds) {
        cmds.register(plugin.getPluginMeta(),
                Commands.literal("spawn").executes(ctx -> execute(ctx.getSource().getSender()))
                        .requires(ctx -> ctx.getSender().hasPermission("spawn.use")).build(), null, List.of()
        );
    }

    public int execute(CommandSender sender) {
        Player p = (Player) sender;
        FinePosition pos = Position.fine(p.getLocation());
        if (plugin.getDataFile().get().get("spawn") != null) {
            Location loc = (Location) plugin.getDataFile().get().get("spawn");
            plugin.getTask().start(
                    plugin.getConfigFile().get().getLong("tp-delay"),
                    p,
                    loc,
                    pos
            );
            MessageDeliverUtil.sendFormatted(plugin.getConfigFile().get().getString("message.tp-spawn-starting"),
                    p, plugin.getConfigFile().get().getString("color-format"));
            return 1;
        }
        MessageDeliverUtil.sendFormatted(plugin.getConfigFile().get().getString("message.tp-spawn-failure-null"),
                p, plugin.getConfigFile().get().getString("color-format"));
        return 1;
    }
}
