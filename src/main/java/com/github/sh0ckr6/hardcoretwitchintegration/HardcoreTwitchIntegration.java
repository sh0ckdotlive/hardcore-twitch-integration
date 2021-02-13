package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.EventSubNotificationBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.SubscriptionEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.commands.SubscribeCommand;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
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
            case "channel.subscribe":
              final SubscriptionEventBean subscriptionEventBean = gson.fromJson(message, SubscriptionEventBean.class);
              handleSubscription(new Subscription(subscriptionEventBean));
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
    
    new SubscribeCommand(this);
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
  
  public void handleSubscription(Subscription sub) {
    if (sub.tier != 3) {
      player.sendTitle(ChatColor.GOLD + sub.userLogin + ChatColor.RED + (sub.isGift ? " was gifted a tier " + sub.tier + " sub!" : " just subscribed at tier " + sub.tier + "!"), null, 10, 120, 20);
      player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
    } else {
      player.sendTitle(ChatColor.GOLD + sub.userLogin + ChatColor.RED + (sub.isGift ? " was gifted a tier 3 sub!" : " just subscribed at tier 3!"), null, 10, 200, 20);
      player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.25f, 1);
    }
  }
}
