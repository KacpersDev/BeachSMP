package me.koz.beachmode.team;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class TeamHandler {

    public final static HashMap<UUID, List<String>> invites = new HashMap<>();
    public final static HashMap<String, UUID> invitesMap = new HashMap<>();

    public void putPlayer(Player player, String team) {
        if (invites.get(player.getUniqueId()) == null) {
            List<String> i = new ArrayList<>();
            i.add(team);
            invites.put(player.getUniqueId(), i);
            invitesMap.put(team, player.getUniqueId());
        } else {
            List<String> i = invites.get(player.getUniqueId());
            i.add(team);
            invites.replace(player.getUniqueId(), invites.get(player.getUniqueId()), i);
            invitesMap.put(team, player.getUniqueId());
        }
    }

    public boolean containsString(Player player, String team) {
        List<String> teams = invites.get(player.getUniqueId());
        if (teams.contains(team)) {
            return true;
        } else {
            return false;
        }
    }

    public void removePlayer(Player player, String team){
        List<String> i = invites.get(player.getUniqueId());
        i.remove(team);
        invites.replace(player.getUniqueId(), invites.get(player.getUniqueId()), i);
    }

    public void replaceName(Player player, String before, String after){
        List<String> i = invites.get(player.getUniqueId());
        for (String string : i) {
            if (string.equalsIgnoreCase(before)) {
                string.replace(before, after);
            }
        }
    }

    public List<String> getInviteByName(Player player) {
        return invites.get(player.getUniqueId());
    }

}
