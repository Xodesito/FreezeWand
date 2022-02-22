package com.realxode.freezewand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class GuiListener implements Listener {

    private final FreezeWand plugin;

    public GuiListener(FreezeWand plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().equals(CheckGui.getInventory())) return;

        e.setCancelled(true);
        if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)) return;
        PlayerHandler playerHandler = plugin.getPlayerHandler();
        Player target = CheckGui.getTarget();
        switch (e.getSlot()) {
            case 20:
                if (playerHandler.getFrozeneds().containsKey(target.getUniqueId())) {
                    playerHandler.getFrozeneds().put(target.getUniqueId(), playerHandler.getFrozeneds().get(target.getUniqueId()) + 1);
                } else {
                    playerHandler.getFrozeneds().put(target.getUniqueId(), 1);
                }
                Bukkit.getPlayer(target.getName()).kickPlayer("Kicked!");
                playerHandler.getUuidFrozed().remove(target.getUniqueId());
                e.getWhoClicked().closeInventory();
                break;
            case 24:
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFREEZE &8| &7You successfully unfrozed player "
                        + target.getName() + "."));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFREEZE &8| &7You were unfrozen! Remember to respect the rules."));
                plugin.getPlayerHandler().getUuidFrozed().remove(target.getUniqueId());
                playerHandler.getUuidFrozed().remove(target.getUniqueId());
                break;
        }
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(CheckGui.getInventory())) {
            e.setCancelled(true);
        }
    }

}
