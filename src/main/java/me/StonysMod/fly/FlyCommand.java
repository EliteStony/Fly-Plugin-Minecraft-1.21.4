package me.StonysMod.fly;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Dieser Befehl ist nur für Spieler verfügbar.");
            return true;
        }

        if (!player.hasPermission("fly.toggle")) {
            player.sendMessage(ChatColor.RED + "Du hast keine Berechtigung dafür.");
            return true;
        }

        boolean isFlying = player.getAllowFlight();

        if (!isFlying) {
            player.setAllowFlight(true);
            player.setFlying(true);
            Fly.instance.getFlyingPlayers().add(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + "Flugmodus aktiviert.");
        } else {
            if (player.getGameMode() != GameMode.CREATIVE && !player.isOnGround()) {
                Fly.instance.getNoFallDamageAfterFly().add(player.getUniqueId());
            }
            player.setFlying(false);
            player.setAllowFlight(false);
            Fly.instance.getFlyingPlayers().remove(player.getUniqueId());
            player.sendMessage(ChatColor.YELLOW + "Flugmodus deaktiviert.");
        }

        return true;
    }
}