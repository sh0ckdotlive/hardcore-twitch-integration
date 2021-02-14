package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.EventSubNotificationBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.SubscriptionEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.commands.SubscribeCommand;
import com.github.sh0ckr6.hardcoretwitchintegration.gift.Gift;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public final class HardcoreTwitchIntegration extends JavaPlugin {
  
  public WebSocketClient WS;
  public Player player;
  public List<Gift<?>> gifts = new ArrayList<>();
  
  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().log(Level.INFO, "Successfully opened HardcoreTwitchIntegration");
    attemptWebSocketConnection();
    player = Bukkit.getOnlinePlayers().stream().filter(p -> p.getName().equalsIgnoreCase("sh0ckR6")).findFirst().get();
  
    registerCommands();
    registerGifts();
  }
  
  private void registerGifts() {
    
    // Tier 1
    // Good gifts
    final Gift<ItemStack> diamondGift1 = new Gift<>(1);
    diamondGift1.add(new ItemStack(Material.DIAMOND, 4));
    gifts.add(diamondGift1);
  
    final Gift<ItemStack> ironToolsGift1 = new Gift<>(1);
    ironToolsGift1.add(new ItemStack(Material.IRON_SWORD));
    ironToolsGift1.add(new ItemStack(Material.IRON_PICKAXE));
    ironToolsGift1.add(new ItemStack(Material.IRON_SHOVEL));
    ironToolsGift1.add(new ItemStack(Material.IRON_AXE));
    ironToolsGift1.add(new ItemStack(Material.IRON_HOE));
    gifts.add(ironToolsGift1);
    // Neutral gifts
//    final Gift<Slime> slimeGift1 = new Gift<>(1);
//    final Slime slime = (Slime) player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
//    slime.setSize(1);
//    slime.remove();
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
//    slimeGift1.add(slime);
    // Bad gifts
    final Gift<ItemStack> dirtGift1 = new Gift<>(1);
    dirtGift1.add(new ItemStack(Material.DIRT, 500));
    gifts.add(dirtGift1);
    
//    final Gift<Creeper> creeperGift = new Gift<>(1);
//    final Creeper creeper = (Creeper) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
//    creeper.setMaxFuseTicks(40);
//    creeper.ignite();
//    creeper.remove();
//    gifts.add(creeperGift);
    
    // Tier 2
    // Good gifts
    final Gift<ItemStack> diamondGift2 = new Gift<>(2);
    diamondGift2.add(new ItemStack(Material.DIAMOND, 10));
    gifts.add(diamondGift2);
    final Gift<ItemStack> ironGift2 = new Gift<>(2);
    ironGift2.add(new ItemStack(Material.IRON_INGOT, 64));
    gifts.add(ironGift2);
//    final Gift<Wolf> wolfGift2 = new Gift<>(2);
//    final Entity wolfGiftWolf = player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
//    wolfGiftWolf.remove();
//    wolfGift2.add((Wolf) wolfGiftWolf);
//    wolfGift2.add((Wolf) wolfGiftWolf);
//    wolfGift2.add((Wolf) wolfGiftWolf);
//    gifts.add(wolfGift2);
    // Neutral gifts
    final Gift<ItemStack> spongeGift2 = new Gift<>(2);
    spongeGift2.add(new ItemStack(Material.SPONGE, 64));
    gifts.add(spongeGift2);
    // Bad gifts
    final Gift<ItemStack> deadBushGift2 = new Gift<>(2);
    deadBushGift2.add(new ItemStack(Material.DEAD_BUSH, 3000));
    gifts.add(deadBushGift2);
    final Gift<ItemStack> coarseDirtGift2 = new Gift<>(2);
    coarseDirtGift2.add(new ItemStack(Material.COARSE_DIRT, 3000));
    gifts.add(coarseDirtGift2);
//    final Gift<Entity> poweredCreeperGift2 = new Gift<>(2);
//    final Creeper poweredCreeper = (Creeper) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
//    poweredCreeper.setPowered(true);
//    poweredCreeper.remove();
//    poweredCreeperGift2.add(poweredCreeper);
//    gifts.add(poweredCreeperGift2);
//
    // Tier 3
    // Good gifts
    final Gift<ItemStack> netheriteGift3 = new Gift<>(3);
    netheriteGift3.add(new ItemStack(Material.NETHERITE_INGOT, 10));
    gifts.add(netheriteGift3);
    final Gift<ItemStack> diamondArmor3 = new Gift<>(3);
    diamondArmor3.add(new ItemStack(Material.DIAMOND_HELMET));
    diamondArmor3.add(new ItemStack(Material.DIAMOND_LEGGINGS));
    diamondArmor3.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
    diamondArmor3.add(new ItemStack(Material.DIAMOND_BOOTS));
    gifts.add(diamondArmor3);
    final Gift<ItemStack> cowEgg3 = new Gift<>(3);
    cowEgg3.add(new ItemStack(Material.COW_SPAWN_EGG, 32));
    gifts.add(cowEgg3);
    final Gift<ItemStack> witherSkulls3 = new Gift<>(3);
    witherSkulls3.add(new ItemStack(Material.WITHER_SKELETON_SKULL, 3));
    gifts.add(witherSkulls3);
    // Neutral gifts
    final Gift<ItemStack> netheriteHoe3 = new Gift<>(3);
    netheriteHoe3.add(new ItemStack(Material.NETHERITE_HOE));
    gifts.add(netheriteHoe3);
//    final Gift<Entity> rabbit3 = new Gift<>(3);
//    final Entity rabbit = player.getWorld().spawnEntity(player.getLocation(), EntityType.RABBIT);
//    rabbit.remove();
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    rabbit3.add(rabbit);
//    gifts.add(rabbit3);
    // Bad gifts
    final Gift<Runnable> clearInventory3 = new Gift<>(3);
    Runnable clear = new Runnable() {
      @Override
      public void run() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clear sh0ckR6");
      }
    };
    clearInventory3.add(clear);
    gifts.add(clearInventory3);
//    final Gift<Entity> wither3 = new Gift<>(3);
//    final Entity wither = player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
//    wither.remove();
//    wither3.add(wither);
//    gifts.add(wither3);
  }
  
  private void registerCommands() {
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
    List<Gift<?>> gifts = new ArrayList<>();
    for (Gift<?> gift :
            this.gifts) {
      System.out.println("gift.tier = " + gift.tier);
      System.out.println("sub.tier = " + sub.tier);
      if (gift.tier == sub.tier) gifts.add(gift);
    }
    System.out.println(gifts.size());
    Random random = new Random();
    final int giftIndex = random.nextInt(gifts.size());
    Gift<?> gift = gifts.get(giftIndex);
    if (gift.items.stream().allMatch(ItemStack.class::isInstance)) {
      for (ItemStack item : ((List<ItemStack>) gift.items)) {
        player.getInventory().addItem(item);
      }
    } else if (gift.items.stream().allMatch(Entity.class::isInstance)) {
      for (Entity entity : ((List<Entity>) gift.items)) {
        entity.teleport(player.getLocation());
      }
    } else if (gift.items.stream().allMatch(Runnable.class::isInstance)) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        for (Runnable runnable : ((List<Runnable>) gift.items)) {
          runnable.run();
        }
      });
    }
    
    if (sub.tier != 3) {
      player.sendTitle(ChatColor.GOLD + sub.userLogin + ChatColor.RED + (sub.isGift ? " was gifted a tier " + sub.tier + " sub!" : " just subscribed at tier " + sub.tier + "!"), null, 10, 120, 20);
      player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
      
    } else {
      player.sendTitle(ChatColor.GOLD + sub.userLogin + ChatColor.RED + (sub.isGift ? " was gifted a tier 3 sub!" : " just subscribed at tier 3!"), null, 10, 200, 20);
      player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.25f, 1);
    }
  }
  
  private void attemptWebSocketConnection() {
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
  }
}
