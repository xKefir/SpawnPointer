package org.minerail.spawnPointer.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.minerail.spawnPointer.SpawnPointer;

import java.io.File;

public class ConfigFile {
    private final SpawnPointer plugin;
    private YamlConfiguration config;
    private File cf;

    public ConfigFile(SpawnPointer plugin) {
        this.plugin = plugin;
        cf = new File(plugin.getDataFolder() + "/config.yml");
        if (!cf.exists()) {
            plugin.saveResource("config.yml", false);
        }
        reload();
    }

    public YamlConfiguration get() { return config; }
    public void reload() {
        try {
            config = YamlConfiguration.loadConfiguration(cf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

