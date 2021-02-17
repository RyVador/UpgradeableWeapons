package me.ryvador.upgradeableweapons.Commands;

import me.ryvador.upgradeableweapons.Configs.PlayerDataFile;
import me.ryvador.upgradeableweapons.UpgradeableWeapons;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BypassCommand implements CommandExecutor {

    UpgradeableWeapons plugin;

    public BypassCommand(UpgradeableWeapons plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player player = (Player) sender;

            if(player.hasPermission("upgradeableweapons.bypass")){

                if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".data" + ".isBypassing")){
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".data" + ".isBypassing", true);
                    player.sendMessage(ChatColor.AQUA + "Now bypassing cooldowns!");
                } else {
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".data" + ".isBypassing", false);
                    player.sendMessage(ChatColor.AQUA + "No longer bypassing cooldowns!");
                }

            }


        }

        return false;
    }
}
