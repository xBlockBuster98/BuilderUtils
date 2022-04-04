package dev.blockbuster.builderutils.commands;

import dev.blockbuster.builderutils.BuilderUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.text;

public class Builder implements CommandExecutor {
    private final BuilderUtils plugin;

    public Builder(BuilderUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("builderutils.admin")) {
                if (args.length != 0) {
                    player.sendMessage(text("Command usage: /builder")
                            .color(NamedTextColor.RED));
                }

                if (plugin.getBuilders().contains(player.getUniqueId())) {
                    plugin.getBuilders().remove(player.getUniqueId());
                    player.sendMessage(text("You are no longer a builder.")
                            .color(NamedTextColor.GREEN));
                } else {
                    plugin.getBuilders().add(player.getUniqueId());
                    player.sendMessage(text("You are now in builder mode!")
                            .color(NamedTextColor.RED));
                }
            } else {
                player.sendMessage(text("You do not have permission to use this command.")
                        .color(NamedTextColor.RED));
            }
        } else {
            plugin.getLogger().severe("Only players can use this command!");
        }

        return true;
    }
}
