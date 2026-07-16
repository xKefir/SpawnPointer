package org.minerail.spawnPointer.task;

import io.papermc.paper.math.FinePosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.minerail.spawnPointer.SpawnPointer;
import org.minerail.spawnPointer.util.MessageDeliverUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TpTask {
    private SpawnPointer plugin;
    private Map<UUID, BukkitTask> taskMap = new HashMap<>();
    private Map<UUID, FinePosition> posMap = new HashMap<>();

    public TpTask(SpawnPointer plugin) {
        this.plugin = plugin;
    }

    public void start(long delay, Player p, Location loc, FinePosition pos) {
        posMap.put(p.getUniqueId(), pos);
        taskMap.put(p.getUniqueId(), Bukkit.getScheduler().runTaskLater(plugin, () -> {
            p.teleport(loc);
            MessageDeliverUtil.sendFormatted(plugin.getConfigFile().get().getString("message.tp-spawn-success"),
                    p, plugin.getConfigFile().get().getString("color-format"));
            taskMap.remove(p.getUniqueId());
            posMap.remove(p.getUniqueId());
        }, (delay * 20)));
    }

    public void stop(Player p) {
        BukkitTask t = taskMap.get(p.getUniqueId());
        if (t != null) {
            t.cancel();
            taskMap.remove(p.getUniqueId());
            posMap.remove(p.getUniqueId());
        }
    }

    public FinePosition getPos(Player p) {
        return posMap.get(p.getUniqueId());
    }

    public boolean hasTask(Player p) {
        return taskMap.containsKey(p.getUniqueId());
    }

    public BukkitTask getBukkitTaskFor(Player p) {
        return this.taskMap.get(p.getUniqueId());
    }
}
