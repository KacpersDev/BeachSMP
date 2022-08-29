package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.Team;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
public class TeamShowCommand extends SubCommand {

    private final Core core;

    public TeamShowCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public void perform(Player player, String[] args) {


        if (args.length > 1) {
            Team team = new Team(this.core, args[1]);
            TeamUser teamUser = new TeamUser(this.core);
            if (!team.exists()) {
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.not-exists")));
                return;
            }

            Location homeLocation = team.getLocation();
            Player leader = teamUser.getLeader(args[1]);
            List<Player> members = teamUser.getPlayersFromTeam(args[1]);
            List<Player> coLeaders = teamUser.getCoLeaders(args[1]);
            List<Player> captains = teamUser.getCaptains(args[1]);

            if (coLeaders != null) {
                for (Player coleaders : members) {
                    members.remove(coleaders);
                }
            }
            if (captains != null) {
                for (Player captain : captains) {
                    members.remove(captain);
                }
            }

            String memberString = String.valueOf(members);
            String captainString = String.valueOf(captains);
            String coLeaderString = String.valueOf(coLeaders);

            for (final String i : this.core.getTeamsSettingsConfiguration().getStringList("messages.show")) {

                if (memberString == null) {
                    memberString = " ";
                }

                if (captainString == null) {
                    captainString = " ";
                }

                if (coLeaderString == null) {
                    coLeaderString = " ";
                }

                player.sendMessage(CC.translate(i)
                        .replace("%home_x%", String.valueOf(homeLocation.getX()))
                        .replace("%home_y%", String.valueOf(homeLocation.getY()))
                        .replace("%home_z%", String.valueOf(homeLocation.getZ()))
                        .replace("%leader%", leader.getName())
                        .replace("%co-leaders%", coLeaderString)
                        .replace("%captains%", captainString)
                        .replace("%members%", memberString)
                        .replace("%team%", args[1]));
            }
        }
    }
}
