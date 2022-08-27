package me.koz.beachsmp.combat.command;

import lombok.Getter;
import me.koz.beachsmp.Core;
import me.koz.beachsmp.combat.Combat;
import me.koz.beachsmp.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
@SuppressWarnings("ALL")
public class CombatCommand implements CommandExecutor {

    private final Core core;

    public CombatCommand(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,  String label,  String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (Combat.combat.containsKey(player.getUniqueId())) {
            long remaining = Combat.combat.get(player.getUniqueId()) - System.currentTimeMillis();
            player.sendMessage(CC.translate(this.core.getCombatConfiguration().getString("timer.timer")
                    .replace("%player%", player.getName())
                    .replace("%timer%", String.valueOf(remaining / 1000L))));
        } else {
            player.sendMessage(CC.translate(this.core.getCombatConfiguration().getString("timer.no-timer")));
        }

        return true;
    }
}
