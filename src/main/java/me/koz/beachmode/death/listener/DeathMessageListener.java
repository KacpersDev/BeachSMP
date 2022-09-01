package me.koz.beachmode.death.listener;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@Getter
public class DeathMessageListener implements Listener {

    private final Core core;

    public DeathMessageListener(Core core){
        this.core = core;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof Player && event.getEntity().getKiller() != null) {
            event.setDeathMessage(CC.translate(this.core.getConfig()
                    .getString("death.message")
                    .replace("%player%", event.getEntity().getPlayer().getName())
                    .replace("%killer%", event.getEntity().getKiller().getName())));
        }
    }
}
