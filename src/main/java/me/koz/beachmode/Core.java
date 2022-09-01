package me.koz.beachmode;

import lombok.Getter;
import me.koz.beachmode.challenge.ChallengeTask;
import me.koz.beachmode.challenge.InventoryTask;
import me.koz.beachmode.challenge.command.ChallengeCommand;
import me.koz.beachmode.challenge.listener.ChallengeListener;
import me.koz.beachmode.chat.ChatFormat;
import me.koz.beachmode.combat.command.CCCommand;
import me.koz.beachmode.combat.command.CombatCommand;
import me.koz.beachmode.combat.listener.CombatListener;
import me.koz.beachmode.death.listener.DeathMessageListener;
import me.koz.beachmode.gold.command.GoldAdminCommand;
import me.koz.beachmode.gold.command.GoldShopCommand;
import me.koz.beachmode.gold.listener.GoldListener;
import me.koz.beachmode.randomteleport.command.RandomTPCommand;
import me.koz.beachmode.team.manager.TeamCommandManager;
import me.koz.beachmode.utils.Config;
import me.koz.beachmode.utils.PAPIExpansions;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
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
    private final File challenge = new File(getDataFolder(), "challenge.yml");
    private final File combat = new File(getDataFolder(), "combat.yml");
    private final File teams = new File(getDataFolder(), "teams.yml");
    private final File teamSettings = new File(getDataFolder(), "teamsettings.yml");
    private final File teamsUser = new File(getDataFolder(), "teamsuser.yml");
    private final FileConfiguration dataConfiguration = new YamlConfiguration();

    private final FileConfiguration challengeConfiguration = new YamlConfiguration();
    private final FileConfiguration teamsConfiguration = new YamlConfiguration();
    private final FileConfiguration teamsUserConfiguration = new YamlConfiguration();

    private final FileConfiguration teamsSettingsConfiguration = new YamlConfiguration();
    private final FileConfiguration combatConfiguration = new YamlConfiguration();
    private final FileConfiguration regionConfiguration = new YamlConfiguration();
    private final FileConfiguration bossConfiguration = new YamlConfiguration();
    private final FileConfiguration teleportConfiguration = new YamlConfiguration();

    private static Economy econ = null;
    private static Chat chat = null;
    @Override
    @SuppressWarnings("ALL")
    public void onEnable() {
        instance = this;
        this.config();
        this.command();
        this.listener();
        fix();
        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null){
            try{
                getLogger().info("Trying to load PAPI expansion...");
                new PAPIExpansions().register();
                getLogger().info("Success!");
            } catch (Exception e){
                getLogger().severe("Failed!");
                e.printStackTrace();
            }
        }
        setupEconomy();
        setupChat();
        new ChallengeTask().runTaskTimer(this, 0L,20L);
        new InventoryTask().runTaskTimer(this, 0L,20L);
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
        manager.registerEvents(new CombatListener(this),this);
        manager.registerEvents(new ChatFormat(), this);
        manager.registerEvents(new ChallengeListener(), this);
        manager.registerEvents(new DeathMessageListener(this),this);
    }

    private void command(){
        Objects.requireNonNull(getCommand("goldshop")).setExecutor(new GoldShopCommand(this));
        Objects.requireNonNull(getCommand("goldadmin")).setExecutor(new GoldAdminCommand(this));
        getCommand("team").setExecutor(new TeamCommandManager());
        getCommand("ct").setExecutor(new CombatCommand(this));
        getCommand("wild").setExecutor(new RandomTPCommand(this));
        getCommand("cc").setExecutor(new CCCommand());
        getCommand("challenge").setExecutor(new ChallengeCommand());
    }

    private void config(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        new Config(data, dataConfiguration, "data.yml");
        new Config(region, regionConfiguration, "region.yml");
        new Config(boss, bossConfiguration, "boss.yml");
        new Config(teleport, teleportConfiguration, "teleport.yml");
        new Config(combat, combatConfiguration, "combat.yml");
        new Config(teams, teamsConfiguration, "teams.yml");
        new Config(teamSettings, teamsSettingsConfiguration, "teamsettings.yml");
        new Config(teamsUser, teamsUserConfiguration, "teamsuser.yml");
        new Config(challenge, challengeConfiguration, "challenge.yml");
    }

    private void fix(){
        this.reloadConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat(){ return chat; }

    public static Economy getEconomy() {
        return econ;
    }
}
