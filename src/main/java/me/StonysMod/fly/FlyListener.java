package me.StonysMod.fly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class FlyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Fly.instance.getFlyingPlayers().contains(player.getUniqueId())) {
            player.setAllowFlight(true);
            player.setFlying(true);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        UUID uuid = player.getUniqueId();

        if (Fly.instance.getFlyingPlayers().contains(uuid) ||
                Fly.instance.getNoFallDamageAfterFly().remove(uuid)) {
            event.setCancelled(true);
        }
    }
}