package me.koz.beachmode.team;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamUser {

    private final Core core;

    public TeamUser(Core core) {
        this.core = core;
    }

    public String getTeamByPlayer(Player player){

        if (!this.core.getTeamsUserConfiguration().contains("Player." + player.getUniqueId())) {
            return null;
        }

        return this.core.getTeamsUserConfiguration().getString("Player." + player.getUniqueId() + ".team");
    }

    public String getRoleByPlayer(Player player){

        return this.core.getTeamsUserConfiguration().getString("Player." + player.getUniqueId() + ".role");
    }

    public void setTeamByPlayer(Player player, String team, String role){

        this.core.getTeamsUserConfiguration().set("Player." + player.getUniqueId() + ".team", team);
        this.core.getTeamsUserConfiguration().set("Player." + player.getUniqueId() + ".role", role);
        new Config(this.core.getTeamsUser(), this.core.getTeamsUserConfiguration());
    }

    public void removeTeamByPlayer(Player player){
        this.core.getTeamsUserConfiguration().set("Player." + player.getUniqueId(), null);
        new Config(this.core.getTeamsUser(), this.core.getTeamsUserConfiguration());
    }

    public void renameTeamByPlayer(Player player, String rename, String before) {
        if (this.core.getTeamsUserConfiguration().getString("Player." + player.getUniqueId() + ".team").equalsIgnoreCase(before)) {
            this.core.getTeamsUserConfiguration().getString("Player." + player.getUniqueId() + ".team").replace(before, rename);
            new Config(this.core.getTeamsUser(), this.core.getTeamsUserConfiguration());
        }
    }

    public void leave(Player player){
        this.core.getTeamsUserConfiguration().set("Player." + player.getUniqueId(), null);
        new Config(this.core.getTeamsUser(), this.core.getTeamsUserConfiguration());
    }

    public void setRoleByPlayer(Player coleader, String role) {
        this.core.getTeamsUserConfiguration().set("Player." + coleader.getUniqueId() + ".role", role);
        new Config(this.core.getTeamsUser(), this.core.getTeamsUserConfiguration());
    }

    public void setPlayerTeam(Player player, String team, String role) {
        this.core.getTeamsUserConfiguration().set("Player." + player.getUniqueId() + ".team", team);
        this.core.getTeamsUserConfiguration().set("Player." + player.getUniqueId() + ".role", "member");
        new Config(this.core.getTeamsUser(), this.core.getTeamsUserConfiguration());
    }

    public List<Player> getPlayersFromTeam(String team) {

        for (String uuid : this.core.getTeamsUserConfiguration().getStringList("Player")) {
            if (this.core.getTeamsUserConfiguration().getString("Player." + uuid + ".team").equalsIgnoreCase(team)) {
                Player member = Bukkit.getPlayer(uuid);
                List<Player> players = new ArrayList<>();
                players.add(member);
                return players;
            }
        }
        return null;
    }

    public Player getLeader(String team){

        for (String uuid : this.core.getTeamsUserConfiguration().getStringList("Player")) {
            if (this.core.getTeamsUserConfiguration().getString("Player." + uuid + ".team").equalsIgnoreCase(team)) {
                if (this.core.getTeamsUserConfiguration().getString("Player." + uuid + ".role").equalsIgnoreCase("leader")) {
                    return Bukkit.getPlayer(uuid);
                }
            }
        }

        return null;
    }

    public List<Player> getCoLeaders(String team) {

        for (String uuid : this.core.getTeamsUserConfiguration().getStringList("Player")) {
            if (this.core.getTeamsUserConfiguration().getString("Player." + uuid + ".team").equalsIgnoreCase(team)) {
                if (this.core.getTeamsUserConfiguration().getString("Player." + uuid + ".role").equalsIgnoreCase("coleader")) {
                    List<Player> coLeaders = new ArrayList<>();
                    coLeaders.add(Bukkit.getPlayer(uuid));
                    return coLeaders;
                }
            }
        }

        return null;
    }

    public List<Player> getCaptains(String team) {

        for (String uuid : this.core.getTeamsUserConfiguration().getStringList("Player")) {
            if (this.core.getTeamsUserConfiguration().getString("Player." + uuid + ".team").equalsIgnoreCase(team)) {
                if (this.core.getTeamsUserConfiguration().getString("Player." + uuid + ".role").equalsIgnoreCase("captain")) {
                    List<Player> captains = new ArrayList<>();
                    captains.add(Bukkit.getPlayer(uuid));
                    return captains;
                }
            }
        }

        return null;
    }
}
