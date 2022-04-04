package dev.blockbuster.builderutils.listeners;

import dev.blockbuster.builderutils.BuilderUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;

public class BuilderEvents implements Listener {
    private final BuilderUtils plugin;

    public BuilderEvents(BuilderUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(empty());
        if (player.hasPermission("builderutils.admin")) {
            plugin.getBuilders().add(player.getUniqueId());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void quit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.quitMessage(empty());
        if (plugin.getBuilders().contains(player.getUniqueId())) {
            plugin.getBuilders().remove(player.getUniqueId());
            plugin.getBuilders().clear();
        }
    }

    @EventHandler
    public void place(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE) {
            if (plugin.getBuilders().contains(player.getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage(text("You can't place blocks while in builder mode! Run /builder to place blocks!")
                        .color(NamedTextColor.RED));
            }
        }
    }

    @EventHandler
    public void breakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.CREATIVE) {
            if (plugin.getBuilders().contains(player.getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage(text("You can't break blocks while in builder mode! Run /builder break blocks!")
                        .color(NamedTextColor.RED));
            }
        }
    }
}
