package me.koz.beachsmp.gold.command;

import lombok.Getter;
import me.koz.beachsmp.Core;
import me.koz.beachsmp.gold.Gold;
import me.koz.beachsmp.gold.GoldInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("ALL")
@Getter
public class GoldShopCommand implements CommandExecutor {

    private final Core core;
    private final GoldInventory goldInventory;
    private Gold gold;

    public GoldShopCommand(Core core) {
        this.core = core;
        this.goldInventory = new GoldInventory(this.core);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        this.gold = new Gold(this.core, player);
        this.goldInventory.openCategory(player);
        return true;
    }
}
