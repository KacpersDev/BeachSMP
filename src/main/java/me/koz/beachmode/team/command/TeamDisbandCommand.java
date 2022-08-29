package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.Team;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Getter
public class TeamDisbandCommand extends SubCommand {

    private final Core core;

    public TeamDisbandCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "disband";
    }

    @Override
    public void perform(Player player, String[] args) {

        TeamUser teamUser = new TeamUser(this.core);
        String teamName = teamUser.getTeamByPlayer(player);
        String role = teamUser.getRoleByPlayer(player);
        if (teamName == null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.no-team")));
            return;
        }

        if (!role.equalsIgnoreCase("leader")) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.permission-leader")));
            player.sendMessage(role);
            return;
        }

        new Team(this.core, teamName).disband();
        teamUser.setTeamByPlayer(player, null, null);

        if (this.core.getTeamsSettingsConfiguration().getBoolean("settings.disbanded-broadcast")) {
            Bukkit.broadcastMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.disbanded")
                    .replace("%team%", teamName)));
        } else {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.disbanded")
                    .replace("%team%", teamName)));
        }
    }
}
