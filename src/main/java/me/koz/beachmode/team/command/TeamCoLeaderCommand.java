package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Getter
public class TeamCoLeaderCommand extends SubCommand {


    private final Core core;

    public TeamCoLeaderCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "coleader";
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

        if (args.length == 1) {
            player.sendMessage(CC.translate("/team coleader <add/remove> <player>"));
        } else if (args[1].equalsIgnoreCase("add")) {
            if (args.length == 2) {
                player.sendMessage(CC.translate("&cPlease provide a player"));
            } else {
                Player coleader = Bukkit.getPlayer(args[2]);
                if (teamUser.getTeamByPlayer(coleader) != null && teamUser.getTeamByPlayer(player).equalsIgnoreCase(teamName)) {
                    teamUser.setRoleByPlayer(coleader, "coleader");
                }
            }
        } else if (args[1].equalsIgnoreCase("remove")) {
            if (args.length == 2) {
                player.sendMessage(CC.translate("&cPlease provide a player"));
            } else {
                Player coleader = Bukkit.getPlayer(args[2]);
                if (teamUser.getTeamByPlayer(coleader) != null && teamUser.getTeamByPlayer(player).equalsIgnoreCase(teamName)) {
                    teamUser.setRoleByPlayer(coleader, "member");
                }
            }
        }
    }
}
