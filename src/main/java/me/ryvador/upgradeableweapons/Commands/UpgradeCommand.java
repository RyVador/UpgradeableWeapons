package me.ryvador.upgradeableweapons.Commands;

import me.ryvador.upgradeableweapons.Configs.PlayerDataFile;
import me.ryvador.upgradeableweapons.UpgradeableWeapons;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UpgradeCommand implements CommandExecutor {

    UpgradeableWeapons plugin;

    public UpgradeCommand(UpgradeableWeapons plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player player = (Player) sender;

            plugin.openSelectGUI(player);



            if(PlayerDataFile.get().contains(player.getUniqueId().toString())){
                return true;
            } else {
                //Player
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".data" + ".isBypassing", false);

                //Swords
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordSharpSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordKnockSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordFireSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordSmiteSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordUnbreakSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordLightning", false);

                //Axe
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeSharpSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeSweepingSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeKnockSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeFireSelected", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".axeData" + ".axeUnbreakSelected", false);

                //Transactions
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordSharpPaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordKnockPaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordFirePaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordSmitePaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordUnbreakPaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordLightningPaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".axeSharpPaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".axeSweepingPaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".axeknockPaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".axeFirePaid", false);
                PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".axeUnbreakPaid", false);

                //Saving
                PlayerDataFile.save();
            }



        } else{
            System.out.println("*Console opens a gui*");
        }

    return true;
    }


}
