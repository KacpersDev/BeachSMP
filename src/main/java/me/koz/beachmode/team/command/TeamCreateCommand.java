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
public class TeamCreateCommand extends SubCommand {

    private final Core core;

    public TeamCreateCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1) {
            Team team = new Team(this.core, args[1]);
            TeamUser teamUser = new TeamUser(this.core);
            if (team.exists()) {
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.exists")
                        .replace("%team%", args[1])));
                return;
            }

            if (teamUser.getTeamByPlayer(player) != null) {
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.in-team")));
                return;
            }

            team.create(player);
            teamUser.setTeamByPlayer(player, args[1], "leader");

            if (this.core.getTeamsSettingsConfiguration().getBoolean("settings.created-broadcast")) {
                Bukkit.broadcastMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.created")
                        .replace("%team%", args[1])));
            } else {
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.created")
                        .replace("%team%", args[1])));
            }

        } else {
            for (final String i : core.getTeamsSettingsConfiguration().getStringList("wrong-usage")) {
                player.sendMessage(CC.translate(i));
            }
        }
    }
}
