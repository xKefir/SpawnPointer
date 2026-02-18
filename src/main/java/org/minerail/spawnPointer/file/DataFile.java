package org.minerail.spawnPointer.file;

import org.bukkit.configuration.file.YamlConfiguration;
import org.minerail.spawnPointer.SpawnPointer;

import java.io.File;
import java.io.IOException;

public class DataFile {
    private final SpawnPointer plugin;
    private YamlConfiguration config;
    private File cf;

    public DataFile(SpawnPointer plugin) {
        this.plugin = plugin;
        cf = new File(plugin.getDataFolder() + "/data.yml");
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
    public void save() {
        try {
            config.save(cf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
