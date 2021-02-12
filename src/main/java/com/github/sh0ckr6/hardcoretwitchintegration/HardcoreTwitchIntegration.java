package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.EventSubNotificationBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainEventBeginBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainEventEndBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.HypeTrainEventProgressBean;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
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
            case "channel.hype_train.begin":
              final HypeTrainEventBeginBean hypeTrainEventBeginBean = gson.fromJson(message, HypeTrainEventBeginBean.class);
              handleHypeTrainStart(new HypeTrainStart(hypeTrainEventBeginBean));
            case "channel.hype_train.progress":
              final HypeTrainEventProgressBean hypeTrainEventProgressBean = gson.fromJson(message, HypeTrainEventProgressBean.class);
              handleHypeTrainProgress(new HypeTrainProgress(hypeTrainEventProgressBean));
            case "channel.hype_train.end":
              final HypeTrainEventEndBean hypeTrainEventEndBean = gson.fromJson(message, HypeTrainEventEndBean.class);
              handleHypeTrainEnd(new HypeTrainEnd(hypeTrainEventEndBean));
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
  
  private void handleHypeTrainStart(HypeTrainStart hypeTrainStart) {
  
  }
  
  private void handleHypeTrainProgress(HypeTrainProgress hypeTrainProgress) {
  
  }
  
  private void handleHypeTrainEnd(HypeTrainEnd hypeTrainEnd) {
    
  }
}
