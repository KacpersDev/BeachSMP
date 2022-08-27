package me.koz.beachsmp.combat.command;

import me.koz.beachsmp.Core;
import me.koz.beachsmp.combat.Combat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        Player player = (Player) sender;

        Combat.combat.put(player.getUniqueId(), System.currentTimeMillis() + (Core.getInstance().getCombatConfiguration().getInt("time")) * 1000L);

        return true;
    }
}
