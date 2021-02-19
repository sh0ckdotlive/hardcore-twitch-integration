package com.github.sh0ckr6.hardcoretwitchintegration.commands;

import com.github.sh0ckr6.hardcoretwitchintegration.HardcoreTwitchIntegration;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.ChannelPointRewardGetBean;
import com.google.gson.Gson;
import okhttp3.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class UnpauseCommand implements CommandExecutor {
  
  HardcoreTwitchIntegration plugin;
  
  public UnpauseCommand(HardcoreTwitchIntegration plugin) {
    this.plugin = plugin;
    plugin.getCommand("unpause").setExecutor(this);
  }
  
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (plugin.configManager.getConfig("config") == null) {
      plugin.getLogger().severe("config.yml was not found! Creating...");
      plugin.configManager.createConfig("config");
      return true;
    }
    FileConfiguration config = plugin.configManager.getConfig("config");
    final String apiKey = config.getString("twitch-token");
    
    Request channelPointRequest = new Request.Builder()
            .url("https://api.twitch.tv/helix/channel_points/custom_rewards?broadcaster_id=188494816&only_manageable_rewards=true")
            .addHeader("client-id", "0pxgd0l3lwfy3xxcom1slhmqjlz6ww")
            .addHeader("Authorization", "Bearer " + apiKey)
            .get()
            .build();
    
    plugin.okHttpClient.newCall(channelPointRequest).enqueue(new UnpauseCallback());
    
    return false;
  }
  
  private class UnpauseCallback implements Callback {
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
      plugin.getLogger().severe("Failed to contact Twitch API to receive list of channel points");
      e.printStackTrace();
    }
    
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) {
      RequestBody unpauseBody = new FormBody.Builder()
              .add("is_paused", "false")
              .build();
      
      FileConfiguration config = plugin.configManager.getConfig("config");
      final String apiKey = config.getString("twitch-token");
      
      Gson gson = new Gson();
      final ChannelPointRewardGetBean rewardGetBean = gson.fromJson(response.body().charStream(), ChannelPointRewardGetBean.class);
      rewardGetBean.data.forEach(reward -> {
        Request channelPointPatchRequest = new Request.Builder()
                .url("https://api.twitch.tv/helix/channel_points/custom_rewards?broadcaster_id=188494816&id=" + reward.id)
                .addHeader("client-id", "0pxgd0l3lwfy3xxcom1slhmqjlz6ww")
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .patch(unpauseBody)
                .build();
        try {
          plugin.okHttpClient.newCall(channelPointPatchRequest).execute();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          response.body().close();
        }
      });
      plugin.player.sendMessage(ChatColor.GREEN + "All rewards are now " + ChatColor.GOLD + "unpaused. " + ChatColor.RED + "Good luck...");
    }
  }
}
