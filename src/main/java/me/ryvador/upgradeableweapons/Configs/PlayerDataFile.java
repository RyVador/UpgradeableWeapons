package me.ryvador.upgradeableweapons.Configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerDataFile {


    private static File file;
    private static FileConfiguration playerData;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("UpgradeableWeapons").getDataFolder(), "playerdata.yml" );

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println(e.getLocalizedMessage());
            }
        }
        playerData = YamlConfiguration.loadConfiguration(file);


    }


    public static FileConfiguration get(){
        return playerData;
    }

    public static void save(){
        try{
            playerData.save(file);
        } catch (IOException e){
            System.out.println("Error saving the file!");
            System.out.println(e.getCause());
        }
    }

    public static void reload(){
        playerData = YamlConfiguration.loadConfiguration(file);
    }

    public static void reset(){

        if(file.exists()){


            try {

                file.delete();
                file.createNewFile();
                playerData.save(file);

            } catch (IOException io){

                System.out.println("Error resetting the file!");
                System.out.println(io);
                System.out.println(io.getCause());

            }



        } else {
            return;
        }

    }



}
