package me.koz.beachmode.gold.command;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.gold.Gold;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("ALL")
@Getter
public class GoldAdminCommand implements CommandExecutor{

    private final Core core;
    private Gold gold;

    public GoldAdminCommand(Core core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("beach.goldadmin"))) return false;

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                usage(player);
            } else if (args[0].equalsIgnoreCase("set")) {
                if (args.length == 1) {
                    usage(player);
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (args.length == 2) {
                        usage(player);
                    } else {
                        int amount = Integer.parseInt(args[2]);
                        this.gold = new Gold(this.core, target);
                        this.gold.setGold(amount);
                        player.sendMessage(CC.translate(this.core.getConfig().getString("messages.set")
                                .replace("%player%", target.getName())
                                .replace("%amount%", String.valueOf(amount))));
                    }
                }
            } else if (args[0].equalsIgnoreCase("add")) {
                if (args.length == 1) {
                    usage(player);
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (args.length == 2) {
                        usage(player);
                    } else {
                        int amount = Integer.parseInt(args[2]);
                        this.gold = new Gold(this.core, target);
                        this.gold.addGold(amount);
                        player.sendMessage(CC.translate(this.core.getConfig().getString("messages.add")
                                .replace("%player%", target.getName())
                                .replace("%amount%", String.valueOf(amount))));
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 1) {
                    usage(player);
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (args.length == 2) {
                        usage(player);
                    } else {
                        int amount = Integer.parseInt(args[2]);
                        this.gold = new Gold(this.core, target);
                        this.gold.removeGold(amount);
                        player.sendMessage(CC.translate(this.core.getConfig().getString("messages.remove")
                                .replace("%player%", target.getName())
                                .replace("%amount%", String.valueOf(amount))));
                    }
                }
            } else if (args[0].equalsIgnoreCase("check")) {
                if (args.length == 1) {
                    usage(player);
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    this.gold = new Gold(this.core, target);
                    int gold = this.gold.getGold();
                    player.sendMessage(CC.translate(this.core.getConfig().getString("messages.check")
                            .replace("%player%", target.getName())
                            .replace("%amount%", String.valueOf(gold))));
                }
            }
        } else {
            if (args.length == 0) {
            } else if (args[0].equalsIgnoreCase("set")) {
                if (args.length == 1) {
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (args.length == 2) {
                    } else {
                        int amount = Integer.parseInt(args[2]);
                        this.gold = new Gold(this.core, target);
                        this.gold.setGold(amount);
                    }
                }
            } else if (args[0].equalsIgnoreCase("add")) {
                if (args.length == 1) {
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (args.length == 2) {
                    } else {
                        int amount = Integer.parseInt(args[2]);
                        this.gold = new Gold(this.core, target);
                        this.gold.addGold(amount);
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (args.length == 1) {
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (args.length == 2) {
                    } else {
                        int amount = Integer.parseInt(args[2]);
                        this.gold = new Gold(this.core, target);
                        this.gold.removeGold(amount);
                    }
                }
            } else if (args[0].equalsIgnoreCase("check")) {
                if (args.length == 1) {
                } else {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    this.gold = new Gold(this.core, target);
                    int gold = this.gold.getGold();
                }
            }
        }

        return true;
    }

    void usage(Player player){

        for (final String i : this.core.getConfig().getStringList("messages.wrong-usage")) {
            player.sendMessage(CC.translate(i));
        }
    }
}
