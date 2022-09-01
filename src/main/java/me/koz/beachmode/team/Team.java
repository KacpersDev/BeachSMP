package me.koz.beachmode.team;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Team {

    private final Core core;
    private final String team;

    public Team(Core core, String team) {
        this.core = core;
        this.team = team;
    }

    public void create(Player leader) {
        this.core.getTeamsConfiguration().set("Team." + team + ".leader", leader.getUniqueId().toString());
        this.core.getTeamsConfiguration().set("Team." + team + ".balance", 0);

        new Config(this.core.getTeams(), this.core.getTeamsConfiguration());

    }

    public boolean exists() {
        return this.core.getTeamsConfiguration().contains("Team." + team);
    }

    public void disband() {
        this.core.getTeamsConfiguration().set("Team." + team, null);
        new Config(this.core.getTeams(), this.core.getTeamsConfiguration());
    }

    public void rename(String name) {
        this.core.getTeamsConfiguration().getString("Team." + team).replace(team, name);
        new Config(this.core.getTeams(), this.core.getTeamsConfiguration());
    }

    public void teleport(Player player) {
        if (getLocation() != null) {
            player.teleport(getLocation());
        }
    }

    public Location getLocation() {

        return new Location(Bukkit.getWorld("world"),
                this.core.getTeamsConfiguration().getInt("Team." + team + ".home.x"),
                this.core.getTeamsConfiguration().getInt("Team." + team + ".home.y"),
                this.core.getTeamsConfiguration().getInt("Team." + team + ".home.z"));
    }

    public void setHome(int x, int y, int z) {
        y = y + 1;
        this.core.getTeamsConfiguration().set("Team." + team + ".home.x", x);
        this.core.getTeamsConfiguration().set("Team." + team + ".home.y", y);
        this.core.getTeamsConfiguration().set("Team." + team + ".home.z", z);
        new Config(this.core.getTeams(), this.core.getTeamsConfiguration());
    }

    public int getBalance() {
        return this.core.getTeamsConfiguration().getInt("Team." + team + ".balance");
    }

    public void setBalance(int amount) {
        this.core.getTeamsConfiguration().set("Team." + team + ".balance", amount);
        new Config(this.core.getTeams(), this.core.getTeamsConfiguration());
    }

    public Player getLeader() {
        return Bukkit.getPlayer(this.core.getTeamsConfiguration().getString("Team." + team + ".leader"));
    }

    public List<String> getMembersOfTeam() {

        final List<String> list = new ArrayList<>();

        if (this.core.getTeamsUserConfiguration().getConfigurationSection("Player") != null) {
            for (String playerUUID : this.core.getTeamsUserConfiguration().getConfigurationSection("Player").getKeys(false)) {
                if (this.core.getTeamsUserConfiguration().getString("Player." + playerUUID + ".team").equalsIgnoreCase(team)) {
                    if (this.core.getTeamsUserConfiguration().getString("Player." + playerUUID + ".role").equalsIgnoreCase("member")) {
                        list.add(Bukkit.getPlayer(playerUUID).getName());
                    }
                }
            }
        }
        return list;
    }

    public List<String> getCaptainsOfTeam() {

        final List<String> list = new ArrayList<>();

        if (this.core.getTeamsUserConfiguration().getConfigurationSection("Player") != null) {
            for (String playerUUID : this.core.getTeamsUserConfiguration().getConfigurationSection("Player").getKeys(false)) {
                if (this.core.getTeamsUserConfiguration().getString("Player." + playerUUID + ".team").equalsIgnoreCase(team)) {
                    if (this.core.getTeamsUserConfiguration().getString("Player." + playerUUID + ".role").equalsIgnoreCase("captain")) {
                        list.add(Bukkit.getPlayer(playerUUID).getName());
                    }
                }
            }
        }
        return list;
    }

    public List<String> getCoLeadersOfTeam() {

        final List<String> list = new ArrayList<>();

        if (this.core.getTeamsUserConfiguration().getConfigurationSection("Player") != null) {
            for (String playerUUID : this.core.getTeamsUserConfiguration().getConfigurationSection("Player").getKeys(false)) {
                if (this.core.getTeamsUserConfiguration().getString("Player." + playerUUID + ".team").equalsIgnoreCase(team)) {
                    if (this.core.getTeamsUserConfiguration().getString("Player." + playerUUID + ".role").equalsIgnoreCase("coleader")) {
                        list.add(Bukkit.getPlayer(playerUUID).getName());
                    }
                }
            }
        }
        return list;
    }
}
