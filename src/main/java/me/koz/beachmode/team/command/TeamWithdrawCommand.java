package me.koz.beachmode.team.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.team.Team;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.team.manager.SubCommand;
import me.koz.beachmode.utils.CC;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;

@Getter
public class TeamWithdrawCommand extends SubCommand {

    private final Core core;

    public TeamWithdrawCommand(Core core) {
        this.core = core;
    }

    @Override
    public String getName() {
        return "withdraw";
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

        if (role.equalsIgnoreCase("leader") || role.equalsIgnoreCase("coleader")
        || role.equalsIgnoreCase("captain")) {
            Team team = new Team(this.core, teamName);
            if (args.length > 1) {
                int amount = Integer.parseInt(args[1]);
                if (team.getBalance() >= amount) {
                    team.setBalance(team.getBalance() - amount);
                    Economy economy = Core.getEconomy();
                    EconomyResponse economyResponse = economy.depositPlayer(player, amount);
                    if (economyResponse.transactionSuccess()) {
                        player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.withdraw")
                                .replace("%amount%", String.valueOf(amount))));
                    }
                }
            }
        } else {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.permission-captain")));
            player.sendMessage(role);
        }
    }
}
