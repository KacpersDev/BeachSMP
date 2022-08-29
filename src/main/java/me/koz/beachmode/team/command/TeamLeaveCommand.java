package me.koz.beachmode.team.command;

import me.koz.beachmode.Core;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.entity.Player;

public class TeamLeaveCommand extends SubCommand {

    private final Core core;

    public TeamLeaveCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "leave";
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

        if (role.equalsIgnoreCase("leader")) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.leave-leader")));
            return;
        }

        new TeamUser(this.core).leave(player);
        player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.left")
                .replace("%team%", teamName)));
    }
}
