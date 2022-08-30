package me.koz.beachmode.team.command;

import me.koz.beachmode.Core;
import me.koz.beachmode.team.Team;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeamHomeCommand extends SubCommand {

    private final Core core;

    public TeamHomeCommand(Core core) {
        this.core = core;
    }


    @Override
    public String getName() {
        return "home";
    }

    @Override
    public void perform(Player player, String[] args) {

        TeamUser teamUser = new TeamUser(this.core);
        String teamName = teamUser.getTeamByPlayer(player);
        Team team = new Team(this.core, teamName);

        if (teamName == null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.no-team")));
            return;
        }

        if (team.getLocation() == null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.no-home")));
            return;
        }

        player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.home-teleport")));

        new BukkitRunnable(){

            int teleportTimer = Core.getInstance().getTeamsSettingsConfiguration().getInt("settings.home-teleport");
            final Location location = player.getLocation();

            @Override
            public void run() {
                teleportTimer--;

                Location current = player.getLocation();
                if (current.getX() != location.getX() || current.getY() != location.getY() || current.getZ() != location.getZ()) {
                    player.sendMessage(CC.translate(Core.getInstance().getTeamsSettingsConfiguration().getString("messages.teleport-moved")));
                    this.cancel();
                }

                if (teleportTimer <= 1) {
                    team.teleport(player);
                    this.cancel();
                }
            }
        }.runTaskTimer(this.core, 0L,20L);
    }
}
