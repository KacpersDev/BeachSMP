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
public class TeamDepositCommand extends SubCommand {

    private final Core core;

    public TeamDepositCommand(Core core) {
        this.core = core;
    }


    @Override
    public String getName() {
        return "deposit";
    }

    @Override
    public void perform(Player player, String[] args) {

        TeamUser teamUser = new TeamUser(this.core);
        String teamName = teamUser.getTeamByPlayer(player);
        if (teamName == null) {
            player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                    .getString("messages.no-team")));
            return;
        }
        Economy economy = Core.getEconomy();

        if (args.length > 1) {
            int amount = Integer.parseInt(args[1]);
            Team team = new Team(this.core, teamName);
            int playerBalance = (int) economy.getBalance(player);
            if (playerBalance < amount) {
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration()
                        .getString("messages.deposit-failed")
                        .replace("%amount%", args[1])));
                return;
            }
            EconomyResponse response = economy.withdrawPlayer(player, amount);
            if (response.transactionSuccess()) {
                team.setBalance(team.getBalance() + amount);
                player.sendMessage(CC.translate(this.core.getTeamsSettingsConfiguration().getString("messages.deposit")
                        .replace("%player%", player.getName())
                        .replace("%amount%", String.valueOf(amount))));

            }
        }
    }
}
