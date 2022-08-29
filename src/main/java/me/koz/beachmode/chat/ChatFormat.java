package me.koz.beachmode.chat;

import me.koz.beachmode.Core;
import me.koz.beachmode.team.TeamUser;
import me.koz.beachmode.utils.CC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        TeamUser teamUser = new TeamUser(Core.getInstance());
        if (teamUser.getTeamByPlayer(event.getPlayer()) == null) {
            event.setFormat(CC.translate(Core.getInstance()
                    .getConfig()
                    .getString("chat-format")
                    .replace("%player%", event.getPlayer().getName())
                    .replace("%rank%", Core.getChat().getPrimaryGroup(event.getPlayer()))
                    .replace("%message%", event.getMessage())));
        } else {
            event.setFormat(CC.translate(Core.getInstance()
                    .getConfig()
                    .getString("chat-format-team")
                    .replace("%player%", event.getPlayer().getName())
                    .replace("%rank%", Core.getChat().getPrimaryGroup(event.getPlayer()))
                    .replace("%message%", event.getMessage())
                    .replace("%team%", teamUser.getTeamByPlayer(event.getPlayer()))));
        }
    }
}
