package me.ryvador.upgradeableweapons.Events;

import me.ryvador.upgradeableweapons.Configs.PlayerDataFile;
import me.ryvador.upgradeableweapons.UpgradeableWeapons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class InteractByEntity implements Listener {

    UpgradeableWeapons plugin;

    public InteractByEntity(UpgradeableWeapons plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void interactByEnetity(PlayerInteractAtEntityEvent e){


        Player player = e.getPlayer();

        if(player.getInventory().getItemInMainHand().getItemMeta().hasLore()){
            if(player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.AQUA + "Right click an entity to spawn a lightning bolt!")){

                final Entity clicked = e.getRightClicked();

                Location loc = clicked.getLocation();
                World world = loc.getWorld();

                if(plugin.cooldowns.containsKey(player.getName())){


                    if(plugin.cooldowns.get(player.getName()) > System.currentTimeMillis()){
                        long timeLeft = (plugin.cooldowns.get(player.getName()) - System.currentTimeMillis()) / 1000;
                        player.sendMessage(ChatColor.RED + "You still have " + timeLeft + " seconds in your cooldown!");
                        return;
                    }

                }

                world.strikeLightning(loc);

                if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".data" + ".isBypassing")){
                    plugin.cooldowns.put(player.getName(), System.currentTimeMillis() + (20 * 1000));
                }


            } else if(player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.AQUA + "Right click a player to effect them with a random effect!")){

                final Entity target = e.getRightClicked();

                final Player clicker = e.getPlayer();

                if(plugin.cooldowns.containsKey(player.getName())){

                    if(plugin.cooldowns.get(player.getName()) > System.currentTimeMillis()){

                        long timeLeft = (plugin.cooldowns.get(player.getName()) - System.currentTimeMillis()) / 1000;
                        player.sendMessage(ChatColor.RED + "You still have " + timeLeft + " seconds left in your cooldown!");
                        return;

                    }

                }

                Random rand = new Random();

                int random = rand.nextInt(6);

                if(target instanceof Player){

                    try {
                        switch (random){

                            case 1:
                                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 2));
                                clicker.sendMessage(ChatColor.GREEN + "You blinded " + ((Player) target).getDisplayName() + "!");
                                break;
                            case 2:
                                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 2));
                                clicker.sendMessage(ChatColor.GREEN + "You poisoned " + ((Player) target).getDisplayName() + "!");
                                break;
                            case 3:
                                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1));
                                clicker.sendMessage(ChatColor.RED + "You accidentally healed " + ((Player) target).getDisplayName() + "!");
                                break;
                            case 4:
                                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 2));
                                clicker.sendMessage(ChatColor.RED + "You harmed " + ((Player) target).getDisplayName() + "!");
                                break;
                            case 5:
                                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 2));
                                clicker.sendMessage(ChatColor.RED + "You slowed " + ((Player) target).getDisplayName() + "!");
                                break;
                            case 6:
                                ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1));
                                clicker.sendMessage(ChatColor.RED + "You weakened " + ((Player) target).getDisplayName() + "!");
                                break;

                        }
                    } catch (ClassCastException cce){
                        //no
                    }

                }

                if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".data" + ".isBypassing")){
                    plugin.cooldowns.put(player.getName(), System.currentTimeMillis() + (15 * 1000));
                }



            }
        }

    }

}
