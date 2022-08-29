package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.TeamHandler;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Getter
public class TeamInviteCommand extends SubCommand {

    private final Core core;
    private final TeamHandler teamHandler;

    public TeamInviteCommand(Core core) {
        this.core = core;
        this.teamHandler = new TeamHandler();
    }

    @Override
    public String getName() {
        return "invite";
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

        if (!role.equalsIgnoreCase("leader") || !role.equalsIgnoreCase("coleader")
        || !role.equalsIgnoreCase("captain")) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.permission-captain")));
            player.sendMessage(role);
            return;
        }

        if (args.length > 1) {
            Player invited = Bukkit.getPlayer(args[1]);
            if (teamUser.getTeamByPlayer(invited).equalsIgnoreCase(teamName)) {
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.in-team")));
                return;
            }

            if (!this.teamHandler.getInviteByName(invited).contains(teamName)) {
                player.sendMessage(CC.translate("&cYou have already invited this player"));
            }

            invited.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.invitation")
                    .replace("%team%", teamName)));

            this.teamHandler.putPlayer(invited, teamName);
        }
    }
}
