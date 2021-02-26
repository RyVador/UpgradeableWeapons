package me.ryvador.upgradeableweapons;


import me.ryvador.upgradeableweapons.Commands.BypassCommand;
import me.ryvador.upgradeableweapons.Commands.ResetCommand;
import me.ryvador.upgradeableweapons.Commands.UpgradeCommand;
import me.ryvador.upgradeableweapons.Configs.PlayerDataFile;
import me.ryvador.upgradeableweapons.Events.InteractByEntity;
import me.ryvador.upgradeableweapons.Events.MenuHandler;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class UpgradeableWeapons extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MenuHandler(this), this);
        getServer().getPluginManager().registerEvents(new InteractByEntity(this), this);
        getCommand("upgrade").setExecutor(new UpgradeCommand(this));
        getCommand("upgradebypass").setExecutor(new BypassCommand(this));
        getCommand("upgraderesetdata").setExecutor(new ResetCommand());

        PlayerDataFile.setup();
        PlayerDataFile.get().options().copyDefaults(true);
        PlayerDataFile.save();

        if (!setupEconomy() ) {
            System.out.println("No economy plugin found! Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy(){
        return econ;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public Map<String, Long> cooldowns = new HashMap<String, Long>();


    public void openSelectGUI(Player player){
        String selectTitle = "Choose a weapon!";
        Inventory select = Bukkit.createInventory(player, 9, selectTitle);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack close = new ItemStack(Material.BARRIER);

        ItemMeta bowmeta = bow.getItemMeta();
        bowmeta.setDisplayName("Select me to upgrade your bow!");
        bow.setItemMeta(bowmeta);

        ItemMeta swordmeta = sword.getItemMeta();
        swordmeta.setDisplayName("Select me to upgrade your sword!");
        sword.setItemMeta(swordmeta);

        ItemMeta axemeta = axe.getItemMeta();
        axemeta.setDisplayName("Select me to upgrade your axe!");
        axe.setItemMeta(axemeta);

        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName("Select me to close this gui!");
        close.setItemMeta(closemeta);


        select.setItem(0, bow);
        select.setItem(1, sword);
        select.setItem(2, axe);
        select.setItem(8, close);



        player.openInventory(select);
    }

    public void openBowInventory(Player player){
        String optionsTitle = "What would you like to upgrade?";
        Inventory options = Bukkit.createInventory(player, 27, optionsTitle);




        player.openInventory(options);


    }

    public void openSwordSelect(Player player){

        String swordTitle = "Choose a sword!";

        Inventory swordSelect = Bukkit.createInventory(player, 9, swordTitle);

        ItemStack nsword = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack isword = new ItemStack(Material.IRON_SWORD);
        ItemStack ssword = new ItemStack(Material.STONE_SWORD);
        ItemStack wsword = new ItemStack(Material.WOODEN_SWORD);
        ItemStack cancel = new ItemStack(Material.BARRIER);

        ItemMeta nswordmeta = nsword.getItemMeta();
        ItemMeta dswordmeta = dsword.getItemMeta();
        ItemMeta iswordmeta = isword.getItemMeta();
        ItemMeta sswordmeta = ssword.getItemMeta();
        ItemMeta wswordmeta = wsword.getItemMeta();
        ItemMeta cancelmeta = cancel.getItemMeta();

        nswordmeta.setDisplayName("Click me to upgrade your Netherite Sword!");
        dswordmeta.setDisplayName("Click me to upgrade your Diamond Sword!");
        iswordmeta.setDisplayName("Click me to upgrade your Iron Sword!");
        sswordmeta.setDisplayName("Click me to upgrade your Stone Sword!");
        wswordmeta.setDisplayName("Click me to upgrade your Wooden sword!");
        cancelmeta.setDisplayName("Click me to cancel!");

        nsword.setItemMeta(nswordmeta);
        dsword.setItemMeta(dswordmeta);
        isword.setItemMeta(iswordmeta);
        ssword.setItemMeta(sswordmeta);
        wsword.setItemMeta(wswordmeta);
        cancel.setItemMeta(cancelmeta);

        swordSelect.setItem(0, dsword);
        swordSelect.setItem(1, isword);
        swordSelect.setItem(2, ssword);
        swordSelect.setItem(3, wsword);
        swordSelect.setItem(4, nsword);
        swordSelect.setItem(8, cancel);

        player.openInventory(swordSelect);

    }

    public void openAxeSelect(Player player){

        String axeTitle = "Pick an axe!";

        Inventory axeSelection = Bukkit.createInventory(player, 9, axeTitle);

        ItemStack naxe = new ItemStack(Material.NETHERITE_AXE);
        ItemStack daxe = new ItemStack(Material.DIAMOND_AXE);
        ItemStack iaxe = new ItemStack(Material.IRON_AXE);
        ItemStack saxe = new ItemStack(Material.STONE_AXE);
        ItemStack waxe = new ItemStack(Material.WOODEN_AXE);
        ItemStack cancel = new ItemStack(Material.BARRIER);

        ItemMeta nswordmeta = naxe.getItemMeta();
        ItemMeta dswordmeta = daxe.getItemMeta();
        ItemMeta iswordmeta = iaxe.getItemMeta();
        ItemMeta sswordmeta = saxe.getItemMeta();
        ItemMeta wswordmeta = waxe.getItemMeta();
        ItemMeta cancelmeta = cancel.getItemMeta();

        nswordmeta.setDisplayName("Click me to upgrade your Netherite Axe!");
        dswordmeta.setDisplayName("Click me to upgrade your Diamond Axe!");
        iswordmeta.setDisplayName("Click me to upgrade your Iron Axe!");
        sswordmeta.setDisplayName("Click me to upgrade your Stone Axe!");
        wswordmeta.setDisplayName("Click me to upgrade your Wooden Axe!");
        cancelmeta.setDisplayName("Click me to cancel!");

        naxe.setItemMeta(nswordmeta);
        daxe.setItemMeta(dswordmeta);
        iaxe.setItemMeta(iswordmeta);
        saxe.setItemMeta(sswordmeta);
        waxe.setItemMeta(wswordmeta);
        cancel.setItemMeta(cancelmeta);

        axeSelection.setItem(0, daxe);
        axeSelection.setItem(1, iaxe);
        axeSelection.setItem(2, saxe);
        axeSelection.setItem(3, waxe);
        axeSelection.setItem(4, naxe);
        axeSelection.setItem(8, cancel);

        player.openInventory(axeSelection);

    }




    public void openSwordUpgradeMenu(Player player){
        boolean swordSharpSelected = PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".swordData" + ".swordSharpSelected");
        boolean swordKnockSelected = PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".swordData" + ".swordKnockSelected");
        boolean swordFireSelected = PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".swordData" + ".swordFireSelected");
        boolean swordSmiteSelected = PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".swordData" + ".swordSmiteSelected");
        boolean swordUnbreakSelected = PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".swordData" + ".swordUnbreakSelected");

        Inventory swordUpgradeMenu = Bukkit.createInventory(player, 27, "Choose your sword upgrades!");

        ItemStack sword = player.getInventory().getItemInMainHand();
        ItemMeta meta = sword.getItemMeta();

        ItemStack sharpness = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack knockback = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack fire = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack smite = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack unbreaking = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemStack nextpage = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        ItemMeta sharpmeta = sharpness.getItemMeta();
        ItemMeta knockmeta = knockback.getItemMeta();
        ItemMeta firemeta = fire.getItemMeta();
        ItemMeta smitemeta = smite.getItemMeta();
        ItemMeta unbreakmeta = unbreaking.getItemMeta();
        ItemMeta closemeta = close.getItemMeta();
        ItemMeta nextpagemeta = nextpage.getItemMeta();


        sharpmeta.setDisplayName(ChatColor.RED + "Sharpness Upgrade!");
        knockmeta.setDisplayName(ChatColor.RED + "Knockback Upgrade!");
        firemeta.setDisplayName(ChatColor.RED + "Fire Aspect Upgrade!");
        smitemeta.setDisplayName(ChatColor.RED + "Smite Upgrade!");
        unbreakmeta.setDisplayName(ChatColor.RED + "Unbreaking Upgrade!");
        closemeta.setDisplayName(ChatColor.RED + "Close");
        nextpagemeta.setDisplayName(ChatColor.AQUA + "Next Page!");


        Economy economy = getEconomy();


        if(swordSharpSelected){
            if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordSharpPaid")){
                EconomyResponse response = economy.withdrawPlayer(player, 1000.0);
                if (response.balance <= 0){
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordSharpSelected", false);
                    PlayerDataFile.save();
                    return;
                } else {
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordSharpSelected", true);
                    PlayerDataFile.save();
                }
                if(response.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "Successfully bought the upgrade!");
                    sharpness.setType(Material.LIME_STAINED_GLASS_PANE);
                    sharpmeta.setDisplayName(ChatColor.GREEN + "Sharpness Upgrade!");
                    sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordSharpPaid", true);
                    PlayerDataFile.save();
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                }
            }
        }
        if(PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordSharpPaid")){
            sharpness.setType(Material.LIME_STAINED_GLASS_PANE);
            sharpmeta.setDisplayName(ChatColor.GREEN + "Sharpness Upgrade!");
            sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
        }
        if(swordKnockSelected){
            if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordKnockPaid")){
                EconomyResponse response = economy.withdrawPlayer(player, 1000.0);
                if (response.balance <= 0){
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordKnockSelected", false);
                    PlayerDataFile.save();
                    return;
                }
                if(response.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "Successfully bought the upgrade!");
                    knockback.setType(Material.LIME_STAINED_GLASS_PANE);
                    knockmeta.setDisplayName(ChatColor.GREEN + "Knockback Upgrade!");
                    sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordKnockPaid", true);
                    PlayerDataFile.save();
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                }
            }
        }
        if(PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordKnockPaid")){
            knockback.setType(Material.LIME_STAINED_GLASS_PANE);
            knockmeta.setDisplayName(ChatColor.GREEN + "Knockback Upgrade!");
            sword.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        }
        if(swordFireSelected){
            if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordFirePaid")){
                EconomyResponse response = economy.withdrawPlayer(player, 1000.0);
                if (response.balance <= 0){
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordFireSelected", false);
                    PlayerDataFile.save();
                    return;
                }
                if(response.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "Successfully bought the upgrade!");
                    fire.setType(Material.LIME_STAINED_GLASS_PANE);
                    firemeta.setDisplayName(ChatColor.GREEN + "Fire Aspect Upgrade!");
                    sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordFirePaid", true);
                    PlayerDataFile.save();
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                }
            }
        }
        if(PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordFirePaid")){
            fire.setType(Material.LIME_STAINED_GLASS_PANE);
            firemeta.setDisplayName(ChatColor.GREEN + "Fire Aspect Upgrade!");
            sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
        }
        if(swordSmiteSelected){
            if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordSmitePaid")){
                EconomyResponse response = economy.withdrawPlayer(player, 1000.0);
                if (response.balance <= 0){
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordSmiteSelected", false);
                    PlayerDataFile.save();
                    return;
                }
                if(response.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "Successfully bought the upgrade!");
                    smite.setType(Material.LIME_STAINED_GLASS_PANE);
                    smitemeta.setDisplayName(ChatColor.GREEN + "Smite Upgrade!");
                    sword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 4);
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordSmitePaid", true);
                    PlayerDataFile.save();
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                }
            }
        }
        if(PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordSmitePaid")){
            smite.setType(Material.LIME_STAINED_GLASS_PANE);
            smitemeta.setDisplayName(ChatColor.GREEN + "Smite Upgrade!");
            sword.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 4);
        }
        if (swordUnbreakSelected){
            if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordUnbreakPaid")){
                EconomyResponse response = economy.withdrawPlayer(player, 1000.0);
                if (response.balance <= 0){
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".swordData" + ".swordUnbreakSelected", false);
                    PlayerDataFile.save();
                    return;
                }
                if(response.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "Successfully bought the upgrade!");
                    unbreaking.setType(Material.LIME_STAINED_GLASS_PANE);
                    unbreakmeta.setDisplayName(ChatColor.GREEN + "Unbreaking Upgrade!");
                    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordUnbreakPaid", true);
                    PlayerDataFile.save();
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                }
            }
        }
        if (PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordUnbreakPaid")){
            unbreaking.setType(Material.LIME_STAINED_GLASS_PANE);
            unbreakmeta.setDisplayName(ChatColor.GREEN + "Unbreaking Upgrade!");
            sword.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
        }

        sharpness.setItemMeta(sharpmeta);
        knockback.setItemMeta(knockmeta);
        fire.setItemMeta(firemeta);
        smite.setItemMeta(smitemeta);
        unbreaking.setItemMeta(unbreakmeta);
        nextpage.setItemMeta(nextpagemeta);

        swordUpgradeMenu.setItem(10, sharpness);
        swordUpgradeMenu.setItem(11, knockback);
        swordUpgradeMenu.setItem(12, fire);
        swordUpgradeMenu.setItem(13, smite);
        swordUpgradeMenu.setItem(14, unbreaking);
        swordUpgradeMenu.setItem(16, nextpage);

        player.openInventory(swordUpgradeMenu);

    }
    public void openSwordMenu2(Player player){

        boolean lightningSelected = PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".swordData" + ".swordLightning");

        Inventory SwordMenu = Bukkit.createInventory(player, 27, "Choose your sword upgrades! (Page 2)");

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.AQUA + "Right click an entity to spawn a lightning bolt!");

        ItemStack sword = player.getInventory().getItemInMainHand();
        ItemMeta swordmeta = sword.getItemMeta();

        ItemStack previousPage = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack lightning = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        ItemMeta previousMeta = previousPage.getItemMeta();
        ItemMeta lightningmeta = lightning.getItemMeta();

        previousMeta.setDisplayName(ChatColor.AQUA + "Previous Page");
        lightningmeta.setDisplayName(ChatColor.RED + "Lightning Strike Upgrade!");

        sword.setItemMeta(swordmeta);
        lightning.setItemMeta(lightningmeta);
        previousPage.setItemMeta(previousMeta);

        Economy economy = getEconomy();

        if(lightningSelected){
            if(!PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordLightningPaid")){
                EconomyResponse response = economy.withdrawPlayer(player, 1000.0);
                if(economy.getBalance(player) < 1000.0){
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                    PlayerDataFile.save();
                    return;
                }

                if(response.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "Successfully bought the upgrade!");
                    lightning.setType(Material.LIME_STAINED_GLASS_PANE);
                    lightningmeta.setDisplayName(ChatColor.GREEN + "Lightning Strike Upgrade!");
                    swordmeta.setLore(lore);
                    sword.setItemMeta(swordmeta);
                    PlayerDataFile.get().set(player.getUniqueId().toString() + ".transactions" + ".swordLightningPaid", true);
                    PlayerDataFile.save();
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough money!");
                }
            }



        }
        if (PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".transactions" + ".swordLightningPaid")){
            lightning.setType(Material.LIME_STAINED_GLASS_PANE);
            lightningmeta.setDisplayName(ChatColor.GREEN + "Lightning Strike Upgrade!");
            swordmeta.setLore(lore);
            sword.setItemMeta(swordmeta);
        }



        SwordMenu.setItem(10, previousPage);
        SwordMenu.setItem(12, lightning);

        player.openInventory(SwordMenu);

    }
    public void openAxeUpgradeMenu(Player player){

        Inventory axeMenu = Bukkit.createInventory(player, 27, "Choose your axe upgrades!");

        ItemStack sharpness = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemStack sweeping = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemStack knockback = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemStack fire = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemStack unbreaking = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemStack next = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        ItemMeta sharpMeta = sharpness.getItemMeta();
        ItemMeta sweepMeta = sweeping.getItemMeta();
        ItemMeta knockmMeta = knockback.getItemMeta();
        ItemMeta fireMeta = fire.getItemMeta();
        ItemMeta unbreakMeta = unbreaking.getItemMeta();
        ItemMeta nextMeta = next.getItemMeta();


        sharpMeta.setDisplayName(ChatColor.RED + "Sharpness Upgrade!");
        sweepMeta.setDisplayName(ChatColor.RED + "Sweeping Edge Upgrade!");
        knockmMeta.setDisplayName(ChatColor.RED + "Knockback Upgrade!");
        fireMeta.setDisplayName(ChatColor.RED + "Fire Aspect Upgrade!");
        unbreakMeta.setDisplayName(ChatColor.RED + "Unbreaking Upgrade!");
        nextMeta.setDisplayName(ChatColor.AQUA + "Next Page!");

        Economy economy = getEconomy();


        player.openInventory(axeMenu);

    }


}
