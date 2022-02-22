package com.realxode.freezewand;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class WandListener implements Listener {

    private final FreezeWand plugin;

    public WandListener(FreezeWand plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent e) {

        Player player = e.getPlayer();
        if (!e.getRightClicked().getType().equals(EntityType.PLAYER)) return;
        Player target = (Player) e.getRightClicked();
        if (!e.getPlayer().hasPermission("freezewand.use")) return;
        FreezeItem freezeItem = new FreezeItem();
        if (!e.getPlayer().getInventory().getItemInHand().isSimilar(freezeItem.getItemStack())) return;

        if (!plugin.getPlayerHandler().getUuidFrozed().contains(target.getUniqueId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFREEZE &8| &7You successfully froze player "
                    + target.getName() + "."));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFREEZE &8| &7You were frozen by admin, " +
                    "connect to Discord to be reviewed!"));
            plugin.getPlayerHandler().getUuidFrozed().add(target.getUniqueId());
            CheckGui checkGui = new CheckGui(plugin, player, target);
            checkGui.openInventory(player);
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFREEZE &8| &7You successfully unfrozed player "
                    + target.getName() + "."));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lFREEZE &8| &7You were unfrozen! Remember to respect the rules."));
            plugin.getPlayerHandler().getUuidFrozed().remove(target.getUniqueId());
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!plugin.getPlayerHandler().getUuidFrozed().contains(e.getPlayer().getUniqueId())) return;
        if (e.getPlayer().isFlying()) return;
        if (!e.getPlayer().isOnGround()) return;
        e.setCancelled(true);
    }

}
