package me.koz.beachsmp;

import lombok.Getter;
import me.koz.beachsmp.combat.command.CCCommand;
import me.koz.beachsmp.combat.command.CombatCommand;
import me.koz.beachsmp.combat.listener.CombatListener;
import me.koz.beachsmp.gold.command.GoldAdminCommand;
import me.koz.beachsmp.gold.command.GoldShopCommand;
import me.koz.beachsmp.gold.listener.GoldListener;
import me.koz.beachsmp.randomteleport.command.RandomTPCommand;
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
    private final File region = new File(getDataFolder(), "region.yml");
    private final File boss = new File(getDataFolder(), "boss.yml");
    private final File teleport = new File(getDataFolder(), "teleport.yml");
    private final File combat = new File(getDataFolder(), "combat.yml");
    private final FileConfiguration dataConfiguration = new YamlConfiguration();
    private final FileConfiguration combatConfiguration = new YamlConfiguration();
    private final FileConfiguration regionConfiguration = new YamlConfiguration();
    private final FileConfiguration bossConfiguration = new YamlConfiguration();
    private final FileConfiguration teleportConfiguration = new YamlConfiguration();
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
        //manager.registerEvents(new BossSpawnListener(this),this);
        manager.registerEvents(new CombatListener(this),this);
    }

    private void command(){
        Objects.requireNonNull(getCommand("goldshop")).setExecutor(new GoldShopCommand(this));
        Objects.requireNonNull(getCommand("goldadmin")).setExecutor(new GoldAdminCommand(this));
        getCommand("ct").setExecutor(new CombatCommand(this));
        getCommand("wild").setExecutor(new RandomTPCommand(this));
        getCommand("cc").setExecutor(new CCCommand());
    }

    private void config(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        new Config(data, dataConfiguration, "data.yml");
        new Config(region, regionConfiguration, "region.yml");
        new Config(boss, bossConfiguration, "boss.yml");
        new Config(teleport, teleportConfiguration, "teleport.yml");
        new Config(combat, combatConfiguration, "combat.yml");
    }

    private void fix(){
        this.reloadConfig();
    }
}
