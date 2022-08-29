package me.koz.beachmode.bosses.listener;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.bosses.Boss;
import me.koz.beachmode.region.RegionManager;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

@Getter
public class BossSpawnListener implements Listener {

    private final Core core;
    private final RegionManager regionManager;
    private final Boss boss;
    public BossSpawnListener(Core core) {
        this.core = core;
        this.regionManager = new RegionManager(this.core);
        this.boss = new Boss(this.core);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        this.boss.spawnBossByServer();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) return;

            Player player = (Player) event.getDamager();
            if (!Boss.hits.containsKey(player.getUniqueId())) {
                Boss.hits.put(player.getUniqueId(), 1);
            } else {
                Boss.hits.replace(player.getUniqueId(), Boss.hits.get(player.getUniqueId()), Boss.hits.get(player.getUniqueId()) + 1);
            }
            if (Boss.armorStand != null) {
                Boss.armorStand.remove();
            }
            int bossValue = Boss.bosses.get(event.getEntity().getType());
            LivingEntity livingEntity = Boss.entries.get(bossValue);
            if (livingEntity.getType() == EntityType.ZOMBIE && event.getEntity().getEntityId() == bossValue) {
                ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world")).spawnEntity(event.getEntity().getLocation(), EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(livingEntity.getHealth())))));
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(livingEntity.getHealth())))));
                livingEntity.setCustomNameVisible(false);
                //armorStand.remove();
            } else if (livingEntity.getType() == EntityType.SKELETON && event.getEntity().getEntityId() == bossValue) {
                ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world")).spawnEntity(event.getEntity().getLocation(), EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(livingEntity.getHealth())))));
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(livingEntity.getHealth())))));
                livingEntity.setCustomNameVisible(false);
                //armorStand.remove();
            } else if (livingEntity.getType() == EntityType.SPIDER && event.getEntity().getEntityId() == bossValue) {
                ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world")).spawnEntity(event.getEntity().getLocation(), EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(livingEntity.getHealth())))));
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(livingEntity.getHealth())))));
                livingEntity.setCustomNameVisible(false);
                //armorStand.remove();1
            }
        }
    }
}
