package com.github.sh0ckr6.hardcoretwitchintegration.managers;

import com.github.sh0ckr6.hardcoretwitchintegration.HardcoreTwitchIntegration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
  
  public FileConfiguration mainConfig;
  public FileConfiguration channelPointsConfig;
  public FileConfiguration bitsConfig;
  public FileConfiguration subsConfig;
  public File mainConfigFile;
  public File channelPointsConfigFile;
  public File bitsConfigFile;
  public File subsConfigFile;
  
  public void setup(HardcoreTwitchIntegration plugin) {
    if (!plugin.getDataFolder().exists()) {
      plugin.getDataFolder().mkdir();
    }
    
    mainConfigFile = new File(plugin.getDataFolder(), "config.yml");
    channelPointsConfigFile = new File(plugin.getDataFolder(), "points.yml");
    bitsConfigFile = new File(plugin.getDataFolder(), "bits.yml");
    subsConfigFile = new File(plugin.getDataFolder(), "subs.yml");
    
    if (!mainConfigFile.exists()) {
      try {
        mainConfigFile.createNewFile();
      } catch (IOException e) {
        plugin.getLogger().severe("Could not create config.yml file");
      }
    }
    if (!channelPointsConfigFile.exists()) {
      try {
        channelPointsConfigFile.createNewFile();
      } catch (IOException e) {
        plugin.getLogger().severe("Could not create points.yml file");
      }
    }
    if (!bitsConfigFile.exists()) {
      try {
        bitsConfigFile.createNewFile();
      } catch (IOException e) {
        plugin.getLogger().severe("Could not create bits.yml file");
      }
    }
    if (!subsConfigFile.exists()) {
      try {
        subsConfigFile.createNewFile();
      } catch (IOException e) {
        plugin.getLogger().severe("Could not create subs.yml file");
      }
    }
    
    mainConfig = YamlConfiguration.loadConfiguration(mainConfigFile);
    mainConfig.options().copyDefaults(true);
    plugin.getLogger().info("Created config.yml");
    
    channelPointsConfig = YamlConfiguration.loadConfiguration(channelPointsConfigFile);
    channelPointsConfig.options().copyDefaults(true);
    plugin.getLogger().info("Created points.yml");
    
    bitsConfig = YamlConfiguration.loadConfiguration(bitsConfigFile);
    bitsConfig.options().copyDefaults(true);
    plugin.getLogger().info("Created bits.yml");
    
    subsConfig = YamlConfiguration.loadConfiguration(subsConfigFile);
    subsConfig.options().copyDefaults(true);
    plugin.getLogger().info("Created subs.yml");
  }
  
  public void saveConfig(FileConfiguration config, File configFile) {
    try {
      config.save(configFile);
    } catch (IOException e) {
      Bukkit.getLogger().severe("Could not save configuration '" + config.getName() + "'.");
    }
  }
  
  public void reloadConfigs() {
    mainConfig = YamlConfiguration.loadConfiguration(mainConfigFile);
    channelPointsConfig = YamlConfiguration.loadConfiguration(channelPointsConfigFile);
    bitsConfig = YamlConfiguration.loadConfiguration(bitsConfigFile);
    subsConfig = YamlConfiguration.loadConfiguration(subsConfigFile);
  }
}