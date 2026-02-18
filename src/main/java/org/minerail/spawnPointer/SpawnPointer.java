package org.minerail.spawnPointer;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;
import org.minerail.spawnPointer.command.CommandSetSpawn;
import org.minerail.spawnPointer.command.CommandSpawn;
import org.minerail.spawnPointer.command.CommandDelSpawn;
import org.minerail.spawnPointer.file.ConfigFile;
import org.minerail.spawnPointer.file.DataFile;
import org.minerail.spawnPointer.listener.EventListener;
import org.minerail.spawnPointer.task.TpTask;

public final class SpawnPointer extends JavaPlugin {
    private ConfigFile configFile;
    private DataFile dataFile;
    private TpTask task;

    @Override
    public void onEnable() {
        initFiles();
        task = new TpTask(this);
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, e -> {
            new CommandDelSpawn(this).register(e.registrar());
            new CommandSpawn(this).register(e.registrar());
            new CommandSetSpawn(this).register(e.registrar());
        });
    }

    private void initFiles() {
        configFile = new ConfigFile(this);
        dataFile = new DataFile(this);
    }

    public TpTask getTask() {
        return task;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }
}
