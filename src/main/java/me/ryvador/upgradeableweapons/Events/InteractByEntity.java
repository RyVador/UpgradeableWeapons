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


            }
        }

    }

}
