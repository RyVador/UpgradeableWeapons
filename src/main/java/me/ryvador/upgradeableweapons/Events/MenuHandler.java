package me.ryvador.upgradeableweapons.Events;

import me.ryvador.upgradeableweapons.Configs.PlayerDataFile;
import me.ryvador.upgradeableweapons.UpgradeableWeapons;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Material.*;

public class MenuHandler implements Listener {


    UpgradeableWeapons plugin;


    public MenuHandler(UpgradeableWeapons plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();



        if (e.getView().getTitle().equalsIgnoreCase("Choose a weapon!")){
            if (e.getCurrentItem() == null){
                return;
            } else{

                switch(e.getCurrentItem().getType()){
                    case DIAMOND_SWORD:
                        player.closeInventory();
                        plugin.openSwordSelect(player);
                        break;

                    case DIAMOND_AXE:
                        player.closeInventory();
                        plugin.openAxeSelect(player);
                        break;

                    case BOW:
                        if(player.getInventory().getItemInMainHand().getType().equals(Material.BOW)){
                            player.closeInventory();
                            plugin.openBowInventory(player);
                        } else {
                            player.sendMessage(ChatColor.RED + "Please hold a bow in your hand while selecting it!");
                        }
                        break;

                    case BARRIER:
                        player.closeInventory();
                        break;
                }
                e.setCancelled(true);

            }
        } else if (e.getView().getTitle().equalsIgnoreCase("Choose a sword!")){
            if (e.getCurrentItem() == null){
                return;
            } else{
                switch (e.getCurrentItem().getType()){

                    case DIAMOND_SWORD:
                        if (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)){
                            plugin.openSwordUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a diamond sword in your hand while selecting this!");
                        }
                        break;

                    case IRON_SWORD:
                        if (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)){
                            plugin.openSwordUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding an iron sword in your hand while selecting this option!");
                        }
                        break;

                    case STONE_SWORD:
                        if (player.getInventory().getItemInMainHand().getType().equals(Material.STONE_SWORD)){
                            plugin.openSwordUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a stone sword in your hand while selecting this option!");
                        }
                        break;

                    case WOODEN_SWORD:
                        if (player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_SWORD)){
                            plugin.openSwordUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a wooden sword in your hand while selecting this option!");
                        }
                        break;

                    case NETHERITE_SWORD:
                        if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)){
                            plugin.openSwordUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a netherite sword in your hand while selecting this option!");
                        }

                    case BARRIER:
                        player.closeInventory();
                        break;

                }
                e.setCancelled(true);
            }
        } else if (e.getView().getTitle().equalsIgnoreCase("Choose an axe!")){
            if (e.getCurrentItem() == null){
                return;
            } else {
                switch (e.getCurrentItem().getType()) {

                    case DIAMOND_AXE:
                        if (player.getInventory().getItemInMainHand().getType().equals(DIAMOND_AXE)){
                            plugin.openAxeUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a diamond axe in your hand while selecting this option!");
                        }
                        break;

                    case IRON_AXE:
                        if (player.getInventory().getItemInMainHand().getType().equals(IRON_AXE)){
                            plugin.openAxeUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding an iron axe in your hand while selecting this option!");
                        }
                        break;

                    case STONE_AXE:
                        if (player.getInventory().getItemInMainHand().getType().equals(STONE_AXE)){
                            plugin.openAxeUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a stone axe in your hand while selecting this option!");
                        }
                        break;

                    case WOODEN_AXE:
                        if (player.getInventory().getItemInMainHand().getType().equals(WOODEN_AXE)){
                            plugin.openAxeUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a wooden axe in your hand while selecting this option!");
                        }
                        break;

                    case NETHERITE_AXE:
                        if (player.getInventory().getItemInMainHand().getType().equals(NETHERITE_AXE)){
                            plugin.openAxeUpgradeMenu(player);
                        } else {
                            player.closeInventory();
                            player.sendMessage(ChatColor.RED + "Make sure you're holding a netehrite axe in your hand while selecting this option!");
                        }
                        break;

                    case BARRIER:
                        player.closeInventory();
                        break;


                }
                e.setCancelled(true);
            }
        } else if (e.getView().getTitle().equalsIgnoreCase("Choose your sword upgrades!")){


            if(e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Sharpness Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordSharpSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openSwordUpgradeMenu(player);

            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Knockback Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordKnockSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openSwordUpgradeMenu(player);

            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Fire Aspect Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordFireSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openSwordUpgradeMenu(player);

            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Smite Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordSmiteSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openSwordUpgradeMenu(player);

            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Unbreaking Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordUnbreakSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openSwordUpgradeMenu(player);
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Next Page!")){
                plugin.openSwordMenu2(player);
            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase("Choose your sword upgrades! (Page 2)")){

            if(e.getCurrentItem() == null){
                return;
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Previous Page")){
                player.closeInventory();
                plugin.openSwordUpgradeMenu(player);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Lightning Strike Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordLightning", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openSwordMenu2(player);
            }
            e.setCancelled(true);

        } else if (e.getView().getTitle().equalsIgnoreCase("Choose your axe upgrades!")){


            if(e.getCurrentItem() == null){
                return;
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Sharpness Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeSharpSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openAxeUpgradeMenu(player);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Sweeping Edge Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeSweepingSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openAxeUpgradeMenu(player);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Knockback Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeKnockSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openAxeUpgradeMenu(player);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Fire Aspect Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeFireSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openAxeUpgradeMenu(player);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Unbreaking Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeUnbreakSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openAxeUpgradeMenu(player);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Next Page!")){
                player.closeInventory();
                plugin.openAxeMenu2(player);
            }
            e.setCancelled(true);

        } else if (e.getView().getTitle().equalsIgnoreCase("Choose your axe upgrades! (Page 2)")){

            if (e.getCurrentItem() == null){
                return;
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Random Effect Upgrade!")){
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeEffectSelected", true);
                player.closeInventory();
                PlayerDataFile.save();
                plugin.openAxeMenu2(player);
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Previous Page!")){
                player.closeInventory();
                plugin.openAxeUpgradeMenu(player);
            }
            e.setCancelled(true);

        }
    }

}
