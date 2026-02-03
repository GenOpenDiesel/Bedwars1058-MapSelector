package me.leoo.bedwars.mapselector.listeners;

import me.leoo.bedwars.mapselector.MapSelector;
import me.leoo.bedwars.mapselector.database.Yaml;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Optimized: Now performs a memory-only check (fast)
        Yaml.checkStored(player);

        // Optimized: Moves the Database check to an async thread to prevent blocking
        Bukkit.getScheduler().runTaskAsynchronously(MapSelector.get(), () -> {
            MapSelector.get().getDatabaseManager().checkStored(player.getUniqueId());
        });
    }
}
