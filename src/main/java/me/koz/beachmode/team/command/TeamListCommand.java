package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.Team;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class TeamListCommand extends SubCommand {

    private final Core core;

    public final Comparator<Team> compareTeamBalance = Comparator.comparing(Team::getTeam);

    public TeamListCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void perform(Player player, String[] args) {

        List<Team> teams = new ArrayList<>();
        if (this.core.getTeamsConfiguration().getConfigurationSection("Team") == null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.list-failed")));
            return;
        }
        for (String values : this.core.getTeamsConfiguration().getConfigurationSection("Team").getKeys(false)) {
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                TeamUser teamUser = new TeamUser(this.core);
                if (teamUser.getTeamByPlayer(onlinePlayers).equalsIgnoreCase(values)) {
                    teams.add(new Team(this.core, values));
                } else {
                    player.sendMessage(teamUser.getTeamByPlayer(onlinePlayers));
                }
            }
        }

        for (Team team : teams) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.list")
                    .replace("%team%", team.getTeam())
                    .replace("%balance%", String.valueOf(team.getBalance()))));
        }
    }
}
