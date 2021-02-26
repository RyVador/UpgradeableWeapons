package me.ryvador.upgradeableweapons.Commands;

import me.ryvador.upgradeableweapons.Configs.PlayerDataFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;


        if(player.hasPermission("upgradeableweapons.resetdata")){

            PlayerDataFile.reset();

        } else {

            player.sendMessage(ChatColor.RED + "You don't have the upgradeableweapons.resetdata permission!");

        }


        return false;
    }
}
