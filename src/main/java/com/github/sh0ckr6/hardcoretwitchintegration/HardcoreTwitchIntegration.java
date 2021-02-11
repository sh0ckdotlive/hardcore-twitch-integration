package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.ChannelPointRedemptionEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.CheerEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.EventSubNotificationBean;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;

public final class HardcoreTwitchIntegration extends JavaPlugin {
  
  public WebSocketClient WS;
  public Player player;
  
  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().log(Level.INFO, "Successfully opened HardcoreTwitchIntegration");
    try {
      WS = new WebSocketClient(new URI("ws://localhost:8082")) {
        @Override
        public void onOpen(ServerHandshake handshakedata) {
          System.out.println("Connection with the WebSocket server has been opened!");
          getLogger().log(Level.INFO, "Connection with the WebSocket server has been opened!");
        }
      
        @Override
        public void onMessage(String message) {
          getLogger().log(Level.INFO, "Message received from WebSocket server: " + message);
          Gson gson = new Gson();
          final EventSubNotificationBean notification = gson.fromJson(message, EventSubNotificationBean.class);
          switch (notification.subscription.type) {
            case "channel.channel_points_custom_reward_redemption.add":
              getLogger().log(Level.INFO, "Made it to handling channel point redemptions");
              final ChannelPointRedemptionEventBean channelPointRedemptionBean = gson.fromJson(message, ChannelPointRedemptionEventBean.class);
              handleRedemption(new ChannelPointRedemption(channelPointRedemptionBean));
              break;
            case "channel.cheer":
              final CheerEventBean cheerEventBean = gson.fromJson(message, CheerEventBean.class);
              handleCheer(new Cheer(cheerEventBean));
          }
          
        }
      
        @Override
        public void onClose(int code, String reason, boolean remote) {
          getLogger().log(Level.INFO, "Connection with the WebSocket server has been closed!");
        }
      
        @Override
        public void onError(Exception ex) {
          getLogger().log(Level.INFO, "An error has occurred while attempting to connect to the WebSocket server:");
          ex.printStackTrace();
        }
      };
      WS.connect();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    player = Bukkit.getOnlinePlayers().stream().filter(p -> p.getName().equalsIgnoreCase("sh0ckR6")).findFirst().get();
  }
  
  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (label.equalsIgnoreCase("ws")) {
      WS.send(args[0]);
    }
    return false;
  }
  
  private void handleRedemption(ChannelPointRedemption redemption) {
    if (redemption.rewardTitle.equalsIgnoreCase("Test Reward from CLI")) {
      /* FIXME: Creeper Spawn */
//      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " spawned a creeper" + ChatColor.GRAY + " on you!");
//      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
//        final Creeper creeper = (Creeper) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
//        creeper.setMaxFuseTicks(40);
//        creeper.ignite();
//      });
      
      /* FIXME: Freeze! */
      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " frozen" + ChatColor.GRAY + " you for 30 seconds!");
      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 199, true, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 199, true, false, true));
      });
    }
  }
  
  private void handleCheer(Cheer cheer) {
    player.sendMessage(ChatColor.GOLD + cheer.userName + ChatColor.GRAY + " has cheered " + (cheer.amount < 100 ? ChatColor.GRAY : cheer.amount < 1000 ? ChatColor.LIGHT_PURPLE : cheer.amount < 5000 ? ChatColor.GREEN : cheer.amount < 10000 ? ChatColor.BLUE : ChatColor.RED) + cheer.amount + ChatColor.GRAY + " bits!");
    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
  }
}
