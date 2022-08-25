package me.koz.beachsmp;

import lombok.Getter;
import me.koz.beachsmp.gold.command.GoldAdminCommand;
import me.koz.beachsmp.gold.command.GoldShopCommand;
import me.koz.beachsmp.gold.listener.GoldListener;
import me.koz.beachsmp.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

@Getter
public final class Core extends JavaPlugin {

    private static Core instance;
    private final File data = new File(getDataFolder(), "data.yml");
    private final FileConfiguration dataConfiguration = new YamlConfiguration();
    @Override
    public void onEnable() {
        instance = this;
        this.config();
        this.command();
        this.listener();
        fix();
    }

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void listener(){
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new GoldListener(this),this);
    }

    private void command(){
        Objects.requireNonNull(getCommand("goldshop")).setExecutor(new GoldShopCommand(this));
        Objects.requireNonNull(getCommand("goldadmin")).setExecutor(new GoldAdminCommand(this));
    }

    private void config(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        new Config(data, dataConfiguration, "data.yml");
    }

    private void fix(){
        this.reloadConfig();
    }
}
