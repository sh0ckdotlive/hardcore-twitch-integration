package com.github.sh0ckr6.hardcoretwitchintegration.commands;

import com.github.sh0ckr6.hardcoretwitchintegration.HardcoreTwitchIntegration;
import com.github.sh0ckr6.hardcoretwitchintegration.gift.Gift;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubscribeCommand implements CommandExecutor, TabCompleter {
  
  HardcoreTwitchIntegration plugin;
  
  public SubscribeCommand(HardcoreTwitchIntegration plugin) {
    this.plugin = plugin;
    plugin.getCommand("resub").setExecutor(this);
    plugin.getCommand("resub").setTabCompleter(this);
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
    String userLogin = args[0];
    int tier = Integer.parseInt(args[1]);
    int months = Integer.parseInt(args[2]);
    boolean isGift = Boolean.parseBoolean(args[3]);
    
    if (tier != 3) {
      plugin.player.sendTitle(ChatColor.GOLD + userLogin + ChatColor.RED + (isGift ? " was gifted a tier " + tier + " sub!" : " just subscribed at tier " + tier + "!"), ChatColor.GRAY + "They have been subscribed for " + ChatColor.RED + months + ChatColor.GRAY + " months!", 10, 120, 20);
      plugin.player.playSound(plugin.player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
    } else {
      plugin.player.sendTitle(ChatColor.GOLD + userLogin + ChatColor.RED + (isGift ? " was gifted a tier 3 sub!" : " just subscribed at tier 3!"), ChatColor.GRAY + "They have been subscribed for " + ChatColor.RED + months + ChatColor.GRAY + " months!", 10, 200, 20);
      plugin.player.playSound(plugin.player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.25f, 1);
    }
    
    handleSubscription(tier);
    return true;
  }
  
  public void handleSubscription(int tier) {
    List<Gift<?>> gifts = new ArrayList<>();
    for (Gift<?> gift :
            plugin.gifts) {
      System.out.println("gift.tier = " + gift.tier);
      System.out.println("sub.tier = " + tier);
      if (gift.tier == tier) gifts.add(gift);
    }
    System.out.println(gifts.size());
    Random random = new Random();
    final int giftIndex = random.nextInt(gifts.size());
    Gift<?> gift = gifts.get(giftIndex);
    if (gift.items.stream().allMatch(ItemStack.class::isInstance)) {
      for (ItemStack item : ((List<ItemStack>) gift.items)) {
        plugin.player.getInventory().addItem(item);
      }
    } else if (gift.items.stream().allMatch(Entity.class::isInstance)) {
      for (Entity entity : ((List<Entity>) gift.items)) {
        entity.teleport(plugin.player.getLocation());
      }
    } else if (gift.items.stream().allMatch(Runnable.class::isInstance)) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
        for (Runnable runnable : ((List<Runnable>) gift.items)) {
          runnable.run();
        }
      });
    }
  }
  
  @Override
  public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
    if (args.length == 2) {
      List<String> tierComplete = new ArrayList<>();
      for (int i = 1; i <= 3; i++) {
        tierComplete.add(i + "");
      }
      return tierComplete;
    }
    if (args.length == 3) {
      List<String> monthComplete = new ArrayList<>();
      for (int i = 1; i <= 24; i++) {
        monthComplete.add(i + "");
      }
      return monthComplete;
    }
    if (args.length == 4) {
      List<String> giftedComplete = new ArrayList<>();
      giftedComplete.add("true");
      giftedComplete.add("false");
    }
    return null;
  }
}
