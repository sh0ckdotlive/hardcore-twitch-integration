package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.ChannelPointRedemptionEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.EventSubNotificationBean;
import com.github.sh0ckr6.hardcoretwitchintegration.listeners.MainListener;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;

public final class HardcoreTwitchIntegration extends JavaPlugin {
  
  public WebSocketClient WS;
  public Player player;
  public boolean isPreventedFromBreaking;
  public boolean isPreventedFromInteracting;
  
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
    new MainListener(this);
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
      //<editor-fold desc="Spawn Creeper">
//      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " spawned a creeper" + ChatColor.GRAY + " on you!");
//      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
//        final Creeper creeper = (Creeper) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
//        creeper.setMaxFuseTicks(40);
//        creeper.ignite();
//      });
      //</editor-fold>
      //<editor-fold desc="No Breaking Blocks">
//      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " prevented you from breaking blocks" + ChatColor.GRAY + " for 30 seconds!");
//      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
//      isPreventedFromBreaking = true;
//      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
//        isPreventedFromBreaking = false;
//        player.sendMessage(ChatColor.GRAY + "You can now break blocks again!");
//        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
//      }, 600);
      //</editor-fold>
      //<editor-fold desc="No Interacting (Right Click)">
      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " prevented you from interacting with anything" + ChatColor.GRAY + " for 30 seconds!");
      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      isPreventedFromInteracting = true;
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        isPreventedFromInteracting = false;
        player.sendMessage(ChatColor.GRAY + "You can now interact again!");
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      }, 600);
      //</editor-fold>
      //<editor-fold desc="Freeze!">
//      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " frozen" + ChatColor.GRAY + " you for 30 seconds!");
//      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
//      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
//        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 199, true, false, true));
//        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 199, true, false, true));
//      });
      //</editor-fold>
      //<editor-fold desc="Roll the Dice">
      Random random = new Random();
      final int diceRoll = random.nextInt(7) + 1;
      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " rolled the dice!");
      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has rolled a " + ChatColor.RED + diceRoll);
        switch (diceRoll) {
          case 1:
            final Creeper creeper = (Creeper) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
            creeper.setMaxFuseTicks(40);
            creeper.ignite();
            player.sendMessage(ChatColor.RED + "Tick, tick, boom!");
            break;
          case 2:
            player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
            player.sendMessage(ChatColor.GRAY + "Enjoy your " + ChatColor.DARK_GREEN + "poison" + ChatColor.GRAY + "!");
            break;
          case 3:
            player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " was " + ChatColor.RED + "scammed out of their points" + ChatColor.GRAY + "! Better luck next time!");
            break;
          case 4:
            player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 10));
            player.sendMessage(ChatColor.GRAY + "Enjoy your " + ChatColor.WHITE + "10 iron ingots!");
            break;
          case 5:
            player.getInventory().addItem(new ItemStack(Material.DIAMOND));
            player.sendMessage(ChatColor.GRAY + "A " + ChatColor.AQUA + "shiny diamond" + ChatColor.GRAY + " just for you!");
            break;
          case 6:
            final int potionEffect = random.nextInt(PotionEffectType.values().length);
            //noinspection ConstantConditions
            player.addPotionEffect(new PotionEffect(PotionEffectType.getByName(PotionEffectType.values()[potionEffect].getName()), 600, 0, false, true, true));
            player.sendMessage(ChatColor.GRAY + "I hope whatever " + ChatColor.RED + "potion effect " + ChatColor.GRAY + "you just got helps!");
            break;
          case 7:
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 300, 0));
            player.sendMessage(ChatColor.GRAY + "Enjoy your " + ChatColor.RED + "flight" + ChatColor.GRAY + "!");
            break;
        }
      }, 100);
      //</editor-fold>
    }
  }
}
