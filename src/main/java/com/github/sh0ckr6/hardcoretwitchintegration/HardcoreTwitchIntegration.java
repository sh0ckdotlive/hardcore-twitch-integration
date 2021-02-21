package com.github.sh0ckr6.hardcoretwitchintegration;

import com.github.sh0ckr6.hardcoretwitchintegration.beans.ChannelPointRedemptionEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.CheerEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.EventSubNotificationBean;
import com.github.sh0ckr6.hardcoretwitchintegration.beans.SubscriptionEventBean;
import com.github.sh0ckr6.hardcoretwitchintegration.commands.PauseCommand;
import com.github.sh0ckr6.hardcoretwitchintegration.commands.SubscribeCommand;
import com.github.sh0ckr6.hardcoretwitchintegration.commands.UnpauseCommand;
import com.github.sh0ckr6.hardcoretwitchintegration.gift.Gift;
import com.github.sh0ckr6.hardcoretwitchintegration.listeners.MainListener;
import com.github.sh0ckr6.hardcoretwitchintegration.managers.ConfigManager;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
  public boolean isPreventedFromBreaking;
  public boolean isPreventedFromInteracting;
  public List<Gift<?>> gifts = new ArrayList<>();
  public ConfigManager configManager;
  public OkHttpClient okHttpClient;
  
  @Override
  public void onEnable() {
    // Plugin startup logic
    okHttpClient = new OkHttpClient();
    getLogger().log(Level.INFO, "Successfully opened HardcoreTwitchIntegration");
    attemptWebSocketConnection();
    player = Bukkit.getOnlinePlayers().stream().filter(p -> p.getName().equalsIgnoreCase("sh0ckR6")).findFirst().get();
    registerListeners();
    registerCommands();
    registerGifts();
    registerConfigs();
  }
  
  private void registerListeners() {
    new MainListener(this);
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
    new PauseCommand(this);
    new UnpauseCommand(this);
  }
  
  private void registerConfigs() {
    configManager = new ConfigManager(this);
    configManager.createConfig("config");
  }
  
  @Override
  public void onDisable() {
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (label.equalsIgnoreCase("ws")) {
      WS.send(args[0]);
    }
    return false;
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
            case "channel.cheer":
              final CheerEventBean cheerEventBean = gson.fromJson(message, CheerEventBean.class);
              handleCheer(new Cheer(cheerEventBean));
            case "channel.channel_points_custom_reward_redemption.add":
              final ChannelPointRedemptionEventBean channelPointRedemptionEventBean = gson.fromJson(message, ChannelPointRedemptionEventBean.class);
              handleRedemption(new ChannelPointRedemption(channelPointRedemptionEventBean));
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
  private void handleRedemption(ChannelPointRedemption redemption) {
    if (redemption.rewardTitle.equalsIgnoreCase("Spawn Creeper")) {
      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " spawned a creeper" + ChatColor.GRAY + " on you!");
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        final Creeper creeper = (Creeper) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
        creeper.setMaxFuseTicks(40);
        creeper.ignite();
      });
    }
    if (redemption.rewardTitle.equalsIgnoreCase("No Breaking Blocks")) {
      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " prevented you from breaking blocks" + ChatColor.GRAY + " for 30 seconds!");
      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      isPreventedFromBreaking = true;
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        isPreventedFromBreaking = false;
        player.sendMessage(ChatColor.GRAY + "You can now break blocks again!");
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      }, 600);
    }
    if (redemption.rewardTitle.equalsIgnoreCase("No Interacting (Right Click)")) {
      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " prevented you from interacting with anything" + ChatColor.GRAY + " for 30 seconds!");
      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      isPreventedFromInteracting = true;
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        isPreventedFromInteracting = false;
        player.sendMessage(ChatColor.GRAY + "You can now interact again!");
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      }, 600);
    }
    if (redemption.rewardTitle.equalsIgnoreCase("Freeze!")) {
      player.sendMessage(ChatColor.GOLD + redemption.userName + ChatColor.GRAY + " has" + ChatColor.RED + " frozen" + ChatColor.GRAY + " you for 30 seconds!");
      player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99, true, false, true));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 199, true, false, true));
      });
    }
    if (redemption.rewardTitle.equalsIgnoreCase("Roll the Dice")) {
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
    }
    if (redemption.rewardTitle.equalsIgnoreCase("Anvil Rain")) {
      player.sendMessage(ChatColor.GRAY + "And in today's forecast, " + ChatColor.RED + "heavy anvil rain" + ChatColor.GRAY + "!");
      Random random = new Random();
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        for (int x = -5; x <= 5; x++) {
          for (int z = -5; z <= 5; z++) {
            final boolean willSpawnAnvil = random.nextBoolean();
            if (!willSpawnAnvil) continue;
            Location spawnLocation = new Location(player.getWorld(), player.getLocation().getX() + x, player.getLocation().getY() + 20, player.getLocation().getZ() + z);
            player.getWorld().getBlockAt(spawnLocation).setType(Material.ANVIL);
          }
        }
      });
    }
    if (redemption.rewardTitle.equalsIgnoreCase("Slime Party")) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.sendMessage(ChatColor.GREEN + "Slime party!");
        for (int i = 0; i < 10; i++) {
          final Slime slime = (Slime) player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
          slime.setSize(1);
        }
      });
    }
    if (redemption.rewardTitle.equalsIgnoreCase("Play a Sound")) {
      player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1.0f, 1.5f);
    }
    if (redemption.rewardTitle.equalsIgnoreCase("Bee")) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BEE);
      });
    }
    if (redemption.rewardTitle.equalsIgnoreCase("Jumpscare")) {
      player.sendMessage(ChatColor.RED + "Boo!" + ChatColor.GOLD + " ~ " + redemption.userName);
      player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 1);
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 99, true, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 99, true, false, false));
      });
    }
  }
  private void handleCheer(Cheer cheer) {
    player.sendMessage(ChatColor.GOLD + cheer.userName + ChatColor.GRAY + " has cheered " + (cheer.amount < 100 ? ChatColor.GRAY : cheer.amount < 1000 ? ChatColor.LIGHT_PURPLE : cheer.amount < 5000 ? ChatColor.GREEN : cheer.amount < 10000 ? ChatColor.BLUE : ChatColor.RED) + cheer.amount + ChatColor.GRAY + " bits!");
    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
    
    if (cheer.amount == 69) {
      player.sendMessage(ChatColor.GRAY + "nice");
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> player.getWorld().spawnEntity(player.getLocation(), EntityType.SNOWMAN));
    } else if (cheer.amount == 100) {
  
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.getInventory().addItem(new ItemStack(Material.IRON_ORE, 32));
      });
    } else if (cheer.amount == 420) {
      player.sendMessage(ChatColor.GRAY + "Ayyyyyy");
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
      });
    } else if (cheer.amount == 666) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
      });
    } else if (cheer.amount == 911) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 99, false, true, true));
      });
    } else if (cheer.amount == 4200) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
        player.getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
      });
    } else if (cheer.amount == 6666) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
        player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_DRAGON);
      });
    } else if (cheer.amount == 10000) {
      Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill sh0ckR6"));
    }
  }
}
