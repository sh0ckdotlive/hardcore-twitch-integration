package com.github.sh0ckr6.hardcoretwitchintegration.listeners;

import com.github.sh0ckr6.hardcoretwitchintegration.HardcoreTwitchIntegration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MainListener implements Listener {
  
  HardcoreTwitchIntegration plugin;
  
  public MainListener(HardcoreTwitchIntegration plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }
  
  @EventHandler(ignoreCancelled = true)
  public void onBlockBreak(BlockBreakEvent event) {
    if (!plugin.isPreventedFromBreaking) return;
    event.setCancelled(true);
  }
}
