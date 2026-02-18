package org.minerail.spawnPointer.listener;

import io.papermc.paper.math.Position;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.minerail.spawnPointer.SpawnPointer;
import org.minerail.spawnPointer.util.MessageDeliverUtil;

public class EventListener implements Listener {
    private SpawnPointer plugin;
    public EventListener(SpawnPointer plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (plugin.getTask().getBukkitTaskFor(e.getPlayer()) != null &&
                !plugin.getTask().getPos(e.getPlayer()).equals(Position.fine(e.getPlayer().getLocation()))) {
            plugin.getTask().stop(e.getPlayer());
            MessageDeliverUtil.sendFormatted(plugin.getConfigFile().get().getString("message.tp-spawn-failure-moved"),
                    e.getPlayer(), plugin.getConfigFile().get().getString("color-format"));
        }
    }
}
