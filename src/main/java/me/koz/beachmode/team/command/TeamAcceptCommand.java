package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.TeamHandler;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.entity.Player;

@Getter
public class TeamAcceptCommand extends SubCommand {

    private final Core core;
    private final TeamHandler teamHandler;

    public TeamAcceptCommand(Core core) {
        this.core = core;
        this.teamHandler = new TeamHandler();
    }

    @Override
    public String getName() {
        return "accept";
    }

    @Override
    public void perform(Player player, String[] args) {

        TeamUser teamUser = new TeamUser(this.core);
        String teamName = teamUser.getTeamByPlayer(player);

        if (teamName != null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.in-team")));
            return;
        }

        if (args.length > 1) {
            if (teamHandler.getInviteByName(player) == null) {
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                        .getString("messages.no-invite")
                        .replace("%team%", args[1])));
                return;
            }

            teamUser.setTeamByPlayer(player, args[1], "member");
        }
    }
}
