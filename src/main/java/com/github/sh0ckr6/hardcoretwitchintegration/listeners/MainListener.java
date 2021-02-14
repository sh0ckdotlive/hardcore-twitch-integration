package com.github.sh0ckr6.hardcoretwitchintegration.listeners;

import com.github.sh0ckr6.hardcoretwitchintegration.HardcoreTwitchIntegration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
  
  @EventHandler(ignoreCancelled = true)
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (!plugin.isPreventedFromInteracting) return;
    if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
    event.setCancelled(true);
  }
  
  @EventHandler(ignoreCancelled = true)
  public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
    if (!plugin.isPreventedFromInteracting) return;
    event.setCancelled(true);
  }
  
  @EventHandler(ignoreCancelled = true)
  public void onPlayerJoin(PlayerJoinEvent event) {
    if (event.getPlayer().getName().equalsIgnoreCase("sh0ckR6")) {
      plugin.player = event.getPlayer();
    }
  }
}
