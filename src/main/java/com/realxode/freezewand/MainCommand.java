package com.realxode.freezewand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private final FreezeWand plugin;

    public MainCommand(FreezeWand plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Only for players");
            return true;
        }
        Player player = (Player) sender;
        FreezeItem freezeItem = new FreezeItem();
        if (args.length == 0) {
            player.getInventory().addItem(freezeItem.getItemStack());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l[*] &fYou have been given a freeze wand."));
            return true;
        }
        if (args[0].equalsIgnoreCase("check")) {
            if (args.length == 1) {
                player.sendMessage(ChatColor.RED + "You need to specify a player!");
                return true;
            }
            if (Bukkit.getPlayer(args[1]) == null) {
                player.sendMessage(ChatColor.RED + "Invalid player!");
                return true;
            }
            CheckGui checkGui = new CheckGui(plugin, player, Bukkit.getPlayer(args[1]));
            checkGui.openInventory(player);
        }
        return false;
    }
}
