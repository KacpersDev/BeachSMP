package me.koz.beachmode.combat.listener;

import me.koz.beachmode.Core;
import me.koz.beachmode.combat.Combat;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CombatListener implements Listener {

    private final Core core;
    private final Combat combat;

    public CombatListener(Core core) {
        this.core = core;
        this.combat = new Combat(this.core);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (Combat.combat.containsKey(player.getUniqueId()) && !player.isBanned()) {
            Location location = player.getLocation();
            LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
            Combat.entities.put(livingEntity.getUniqueId(), livingEntity);
            Combat.entitiesList.put(player.getUniqueId(), livingEntity.getUniqueId());
            Combat.items.put(livingEntity.getUniqueId(), Arrays.asList(player.getInventory().getContents()));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        if (Combat.entitiesList.containsKey(player.getUniqueId())) {
            List<Entity> livingEntities = event.getPlayer().getLocation().getWorld().getEntities();
            if (!Combat.entitiesList.containsKey(player.getUniqueId())) return;
            UUID entityUUID = Combat.entitiesList.get(player.getUniqueId());
            Entity entity = Combat.entities.get(entityUUID);
            if (livingEntities.contains(entity)) {
                entity.remove();
            } else {
                player.getInventory().clear();
                player.setHealth(0);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Combat.combat.containsKey(player.getUniqueId())) {
            long remaining = Combat.combat.get(player.getUniqueId()) - System.currentTimeMillis();
            if ((remaining / 1000L) <= 0) {
                Combat.combat.remove(player.getUniqueId());
                player.sendMessage(CC.translate(this.core.getCombatConfiguration()
                        .getString("timer.expired")
                        .replace("%player%", player.getName())));
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if (event.getEntity().getKiller() == null && !(event.getEntity().getKiller() instanceof Player)) return;
        Player player = event.getEntity().getKiller();
        Player p = Bukkit.getPlayer(Combat.players.get(player.getUniqueId()));
        UUID uuid = Combat.entitiesList.get(p.getUniqueId());
        LivingEntity entity = Combat.entities.get(uuid);
        if (entity.getType() == EntityType.VILLAGER) {
            Location location = entity.getLocation();
            if (entity.isDead()) {
                for (ItemStack item : Combat.items.get(uuid)) {
                    if (item != null) {
                        location.getWorld().dropItemNaturally(location, item);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if (event.getEntity() != null && event.getEntity() instanceof Player) {
            if (event.getDamager() != null && event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                Player player = (Player) event.getEntity();
                this.combat.updateCooldown(damager,player);
                if (!Combat.players.containsKey(damager.getUniqueId())) {
                    damager.sendMessage(CC.translate(this.core.getCombatConfiguration()
                            .getString("timer.activated")
                            .replace("%player%", damager.getName())));
                }

                if (!Combat.players.containsKey(player.getUniqueId())) {
                    player.sendMessage(CC.translate(this.core.getCombatConfiguration()
                            .getString("timer.activated")
                            .replace("%player%", player.getName())));
                }
                Combat.players.put(damager.getUniqueId(), player.getUniqueId());
            }
        }
    }


    @EventHandler
    public void onMessage(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if (!Combat.combat.containsKey(player.getUniqueId())) return;
        List<String> commands = this.core.getCombatConfiguration().getStringList("disabled-commands");
        commands.forEach(all -> {
            if (event.getMessage().toLowerCase().equalsIgnoreCase("/" + all.toLowerCase())) {
                player.sendMessage(CC.translate("&cFailed to send command while combat"));
                event.setCancelled(true);
            }
        });
    }
}