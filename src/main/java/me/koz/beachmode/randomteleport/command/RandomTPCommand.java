package me.koz.beachmode.randomteleport.command;

import me.koz.beachmode.Core;
import me.koz.beachmode.randomteleport.RandomTP;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

@SuppressWarnings("ALL")
public class RandomTPCommand implements CommandExecutor {

    private final Core core;
    private final RandomTP randomTP;
    private final Random random = new Random();

    public RandomTPCommand(Core core) {
        this.core = core;
        this.randomTP = new RandomTP(this.core);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        int x = this.random.nextInt(this.core.getTeleportConfiguration().getInt("max-x"));
        int z = this.random.nextInt(this.core.getTeleportConfiguration().getInt("max-z"));
        int value = this.random.nextInt(5);
        if (value == 0) {
            x = x * - 1;
        } else if (value == 1) {
            z = z * - 1;
        } else if (value == 2) {
            z = z * - 1;
            x = x * - 1;
        } else if (value == 3) {
            z = z * - 1;
            x = x * 1;
        } else if (value == 4) {
            z = z * 1;
            x = x * - 1;
        } else if (value == 5) {
            z = z * 1;
            x = x * 1;
        }
        Location location = this.randomTP.getHighestBock(player, Bukkit.getWorld("world"),x,z);
        player.sendMessage(CC.translate(this.core.getTeleportConfiguration().getString("message")
                .replace("%player%", player.getName())));
        player.teleport(location);
        return true;
    }
}
