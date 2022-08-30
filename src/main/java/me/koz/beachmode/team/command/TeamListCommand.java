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
import java.util.List;

@Getter
public class TeamListCommand extends SubCommand {

    private final Core core;

    public TeamListCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void perform(Player player, String[] args) {

        final List<Team> teams = new ArrayList<>();

        for (Player online : Bukkit.getOnlinePlayers()) {
            TeamUser teamUser = new TeamUser(Core.getInstance());
            if (teamUser.getTeamByPlayer(online) != null) {
                String userTeam = teamUser.getTeamByPlayer(online);
                Team team = new Team(Core.getInstance(), userTeam);
                teams.add(team);
            }
        }

        for (Team team : teams) {
            String teamName = team.getTeam();
            int balance = team.getBalance();
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.list")
                    .replace("%team%", teamName)
                    .replace("%balance%", String.valueOf(balance))));
        }
    }
}
