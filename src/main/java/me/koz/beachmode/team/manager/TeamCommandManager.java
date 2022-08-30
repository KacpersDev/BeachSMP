package me.koz.beachmode.team.manager;

import me.koz.beachmode.Core;
import me.koz.beachmode.team.command.*;
import me.koz.beachmode.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamCommandManager implements CommandExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();
    private final Core core = Core.getInstance();

    public TeamCommandManager(){
        subCommands.add(new TeamCreateCommand(Core.getInstance()));
        subCommands.add(new TeamDisbandCommand(Core.getInstance()));
        subCommands.add(new TeamLeaveCommand(Core.getInstance()));
        subCommands.add(new TeamSetHomeCommand(Core.getInstance()));
        subCommands.add(new TeamHomeCommand(Core.getInstance()));
        subCommands.add(new TeamCoLeaderCommand(Core.getInstance()));
        subCommands.add(new TeamCaptainCommand(Core.getInstance()));
        subCommands.add(new TeamInviteCommand(Core.getInstance()));
        subCommands.add(new TeamUnInviteCommand(Core.getInstance()));
        subCommands.add(new TeamAcceptCommand(Core.getInstance()));
        subCommands.add(new TeamInvitesCommand(Core.getInstance()));
        subCommands.add(new TeamDepositCommand(Core.getInstance()));
        subCommands.add(new TeamWithdrawCommand(Core.getInstance()));
        subCommands.add(new TeamTopCommand(Core.getInstance()));
        subCommands.add(new TeamListCommand(Core.getInstance()));
    }

    @Override
    @SuppressWarnings("ALL")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (args.length > 0){
                for (int i = 0; i < getSubcommands().size(); i++){
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        getSubcommands().get(i).perform(player, args);
                    }
                }
            }else if(args.length == 0){
                for (final String i : core.getTeamsSettingsConfiguration().getStringList("wrong-usage")) {
                    player.sendMessage(CC.translate(i));
                }
            }

        }


        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subCommands;
    }

}

