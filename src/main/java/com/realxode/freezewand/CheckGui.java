package com.realxode.freezewand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CheckGui {

    private static Inventory inventory = null;
    private static Player target;

    public CheckGui(FreezeWand plugin, Player admin, Player target) {
        CheckGui.target = target;
        PlayerHandler playerHandler = plugin.getPlayerHandler();
        inventory = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&',
                "&4&lTARGET: &8" + target.getName() + " &7(by " + admin.getName() + ")"));
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta meta = itemStack.getItemMeta();

        // Meta creation (APPROVE)
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aApprove &7(Penalize player)"));
        itemStack.setType(Material.EMERALD_BLOCK);
        itemStack.setItemMeta(meta);
        inventory.setItem(20, itemStack);

        // Meta creation (DISAPPROVE)
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cDisapprove &7(Let it free)"));
        itemStack.setType(Material.REDSTONE_BLOCK);
        itemStack.setItemMeta(meta);
        inventory.setItem(24, itemStack);

        // Meta creation (APPROVE)
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        String times = playerHandler.getFrozeneds().containsKey(target.getUniqueId()) ? String.valueOf(playerHandler.getFrozeneds().get(target.getUniqueId())) : "&cNone!";
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Times it has been frozen: &3" + times));
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fInformation about player"));
        itemStack.setType(Material.SIGN);

        itemStack.setItemMeta(meta);
        inventory.setItem(40, itemStack);
    }

    public void openInventory(Player player) {

        player.openInventory(inventory);

    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static Player getTarget() {
        return target;
    }
}
