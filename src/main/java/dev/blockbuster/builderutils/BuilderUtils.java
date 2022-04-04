package dev.blockbuster.builderutils;

import dev.blockbuster.builderutils.commands.Builder;
import dev.blockbuster.builderutils.listeners.BuilderEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;

public final class BuilderUtils extends JavaPlugin {
    private BuilderUtils plugin;

    private HashSet<UUID> builders;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new BuilderEvents(this), this);
        getCommand("builder").setExecutor(new Builder(this));

        builders = new HashSet<>();
    }

    @Override
    public void onDisable() {
        builders.clear();
    }

    public HashSet<UUID> getBuilders() {
        return builders;
    }
}
