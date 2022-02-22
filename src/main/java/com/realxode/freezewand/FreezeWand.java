package com.realxode.freezewand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FreezeWand extends JavaPlugin {

    private PlayerHandler playerHandler;

    @Override
    public void onEnable() {
        playerHandler = new PlayerHandler();
        Bukkit.getPluginManager().registerEvents(new WandListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(this), this);
        getCommand("freezewand").setExecutor(new MainCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }
}
