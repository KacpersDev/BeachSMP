package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.TeamHandler;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.entity.Player;

@Getter
public class TeamInvitesCommand extends SubCommand {

    private final Core core;
    private final TeamHandler teamHandler;

    public TeamInvitesCommand(Core core) {
        this.core = core;
        this.teamHandler = new TeamHandler();
    }

    @Override
    public String getName() {
        return "invites";
    }

    @Override
    public void perform(Player player, String[] args) {

        String invites = String.valueOf(this.teamHandler.getInviteByName(player));
        if (invites != null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.invites")
                    .replace("%teams%", invites)));
        }
    }
}
