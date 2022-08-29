package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.Team;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.entity.Player;

@Getter
public class TeamSetHomeCommand extends SubCommand {

    private final Core core;

    public TeamSetHomeCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "sethome";
    }

    @Override
    public void perform(Player player, String[] args) {

        TeamUser teamUser = new TeamUser(this.core);
        String teamName = teamUser.getTeamByPlayer(player);
        String role = teamUser.getRoleByPlayer(player);
        Team team = new Team(this.core, teamName);

        if (teamName == null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.no-team")));
            return;
        }

        if (role.equalsIgnoreCase("leader") || role.equalsIgnoreCase("coleader") || role.equalsIgnoreCase("captain")) {
            team.setHome((int) player.getLocation().getX(), (int) player.getLocation().getY(), (int) player.getLocation().getZ());
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.home-set")));
        } else {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.permission-captain")));
            player.sendMessage(role);
        }
    }
}
