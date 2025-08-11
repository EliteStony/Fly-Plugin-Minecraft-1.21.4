package me.StonysMod.fly;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;

public final class Fly extends JavaPlugin {

    public static Fly instance;

    private final HashSet<UUID> flyingPlayers = new HashSet<>();
    private final HashSet<UUID> noFallDamageAfterFly = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;
        getCommand("fly").setExecutor(new FlyCommand());
        getServer().getPluginManager().registerEvents(new FlyListener(), this);
        getLogger().info("Fly Plugin aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Fly Plugin deaktiviert!");
    }

    public HashSet<UUID> getFlyingPlayers() {
        return flyingPlayers;
    }

    public HashSet<UUID> getNoFallDamageAfterFly() {
        return noFallDamageAfterFly;
    }
}