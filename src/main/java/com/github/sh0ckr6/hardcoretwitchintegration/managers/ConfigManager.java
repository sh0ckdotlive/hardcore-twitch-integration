package com.github.sh0ckr6.hardcoretwitchintegration.managers;

import com.github.sh0ckr6.hardcoretwitchintegration.HardcoreTwitchIntegration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigManager {
  
  private final HardcoreTwitchIntegration plugin;
  private Map<FileConfiguration, File> configurationFileMap;
  
  public ConfigManager(HardcoreTwitchIntegration plugin) {
    this.plugin = plugin;
    setup();
  }
  
  private void setup() {
    if (!plugin.getDataFolder().exists()) {
      plugin.getDataFolder().mkdir();
    }
  }
  
  public void createConfig(String name) {
    File configFile = new File(plugin.getDataFolder(), name + ".yml");
    if (!configFile.exists()) {
      try {
        configFile.createNewFile();
      } catch (IOException e) {
        plugin.getLogger().severe("Could not create file " + configFile.getName());
        e.printStackTrace();
      }
    }
    FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    configurationFileMap.put(config, configFile);
  }
  
  public FileConfiguration getConfig(String name) {
    if (configurationFileMap.keySet().stream().noneMatch(config -> config.getName().equalsIgnoreCase(name))) return null;
    return configurationFileMap.keySet().stream()
            .filter(config -> config.getName().equalsIgnoreCase(name))
            .findFirst()
            .get();
  }
  
  public void saveConfig(FileConfiguration config) {
    try {
      config.save(configurationFileMap.get(config));
    } catch (IOException e) {
      Bukkit.getLogger().severe("Could not save configuration '" + config.getName() + "'.");
    }
  }
  
  public void reloadConfigs() {
    for (FileConfiguration fileConfiguration : configurationFileMap.keySet()) {
      fileConfiguration = YamlConfiguration.loadConfiguration(configurationFileMap.get(fileConfiguration));
    }
  }
}