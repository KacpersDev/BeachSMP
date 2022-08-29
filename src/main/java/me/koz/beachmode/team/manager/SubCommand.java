package me.koz.beachmode.team.manager;

import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getName();


    //code for the subcommand
    public abstract void perform(Player player, String[] args);

}

