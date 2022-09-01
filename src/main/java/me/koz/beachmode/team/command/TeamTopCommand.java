package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.Team;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@Getter
public class TeamTopCommand extends SubCommand {

    private final Core core;
    public final Comparator<Team> compareTeamBalance = Comparator.comparingLong(Team::getBalance);

    public TeamTopCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "top";
    }

    @Override
    public void perform(Player player, String[] args) {

        List<Team> teams = new ArrayList<>();
        if (this.core.getTeamsConfiguration().getConfigurationSection("Team") == null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.top-failed")));
            return;
        }
        for (String values : this.core.getTeamsConfiguration().getConfigurationSection("Team").getKeys(false)) {
            teams.add(new Team(this.core, values));
        }

        teams.sort(compareTeamBalance);
        Collections.reverse(teams);
        for (int i = 0; i < 9; i++) {
            if (i >= teams.size()) {
                break;
            }
            Team team = teams.get(i);
            int teamNumber = i + 1;
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.top")
                    .replace("%team_number%", String.valueOf(teamNumber))
                    .replace("%team_name%", team.getTeam())
                    .replace("%team_balance%", String.valueOf(team.getBalance()))));
        }
    }
}