package me.koz.beachmode.bosses;

import me.koz.beachmode.Core;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

import java.util.*;

public class Boss {

    private final Core core;
    private final Random random = new Random();
    public static HashMap<UUID, Integer> hits = new HashMap<>();
    public static ArmorStand armorStand;
    public static HashMap<EntityType, Integer> bosses = new HashMap<>();
    public static HashMap<Integer, LivingEntity> entries = new HashMap<>();
    public static List<Integer> ids = new ArrayList<>();

    public Boss(Core core) {
        this.core = core;

    }

    public void spawnBossByPlayer(Player player){

    }

    public void spawnBossByServer() {
        if (!this.core.getRegionConfiguration().getBoolean("random")) {
            Location location = new Location(Bukkit.getWorld("World"),
                    this.core.getRegionConfiguration().getInt("spawn.x"),
                    this.core.getRegionConfiguration().getInt("spawn.y"),
                    this.core.getRegionConfiguration().getInt("spawn.z"));
            int number = random.nextInt(3);

            if (number == 0) {
                Zombie zombie = (Zombie) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ZOMBIE);
                Objects.requireNonNull(zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(this.core.getBossConfiguration().getInt("zombie.health"));
                zombie.setHealth(this.core.getBossConfiguration().getInt("zombie.health"));
                armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world")).spawnEntity(zombie.getLocation(), EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(zombie.getHealth())))));
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(zombie.getHealth())))));
                zombie.setCustomNameVisible(false);
                bosses.put(zombie.getType(), zombie.getEntityId());
                entries.put(zombie.getEntityId(), zombie);
                ids.add(zombie.getEntityId());
            } else if (number == 1) {
                Skeleton skeleton = (Skeleton) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.SKELETON);
                Objects.requireNonNull(skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(this.core.getBossConfiguration().getInt("zombie.health"));
                skeleton.setHealth(this.core.getBossConfiguration().getInt("zombie.health"));
                ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world")).spawnEntity(skeleton.getLocation(), EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(skeleton.getHealth())))));
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(skeleton.getHealth())))));
                skeleton.setCustomNameVisible(false);
                bosses.put(skeleton.getType(), skeleton.getEntityId());
                entries.put(skeleton.getEntityId(), skeleton);
                ids.add(skeleton.getEntityId());
            } else if (number == 2) {
                Spider spider = (Spider) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.SPIDER);
                Objects.requireNonNull(spider.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(this.core.getBossConfiguration().getInt("zombie.health"));
                spider.setHealth(this.core.getBossConfiguration().getInt("zombie.health"));
                ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world")).spawnEntity(spider.getLocation(), EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(spider.getHealth())))));
                armorStand.setGravity(false);
                armorStand.setCustomNameVisible(true);
                armorStand.setCustomName(CC.translate(this.core.getBossConfiguration().getString("name")
                        .replace("%health%", String.valueOf(Math.round(spider.getHealth())))));
                spider.setCustomNameVisible(false);
                bosses.put(spider.getType(), spider.getEntityId());
                entries.put(spider.getEntityId(), spider);
                ids.add(spider.getEntityId());
            }
        }
    }
}

