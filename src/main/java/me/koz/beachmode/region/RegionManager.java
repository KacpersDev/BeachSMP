package me.koz.beachmode.region;

import lombok.Getter;
import me.koz.beachmode.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;
import java.util.Random;

@Getter
public class RegionManager {

    private final Core core;
    private final Random random = new Random();

    public static Location locationMob;

    public RegionManager(Core core) {
        this.core = core;
    }

    private boolean spawnRandom(){
        return this.core.getRegionConfiguration().getBoolean("spawn-random");
    }

    public void spawnBoss(){
        Location location;
        if (spawnRandom()) {
            location = bossSpawnRandomLocation();
        } else {
            location = bossSpawnLocation();
        }
        locationMob = location;

    }
    private Location bossSpawnRandomLocation(){

        String world = this.core.getRegionConfiguration().getString("random.world");
        int randomX = random.nextInt(this.core.getRegionConfiguration().getInt("random.x"));
        int randomY = random.nextInt(this.core.getRegionConfiguration().getInt("random.y"));
        int randomZ = random.nextInt(this.core.getRegionConfiguration().getInt("random.Z"));

        for (final String i : Objects.requireNonNull(this.core.getRegionConfiguration().getConfigurationSection("location-radius")).getKeys(false)) {
            if (randomX < this.core.getRegionConfiguration().getInt("location-radius." + i + ".location.x")
            || (randomY < this.core.getRegionConfiguration().getInt("location-radius." + i + ".location.y")
            || (randomZ < this.core.getRegionConfiguration().getInt("location-radius." + i + ".location.z")))) {
                do {
                    randomX = random.nextInt(this.core.getRegionConfiguration().getInt("random.x"));
                    randomY = random.nextInt(this.core.getRegionConfiguration().getInt("random.y"));
                    randomZ = random.nextInt(this.core.getRegionConfiguration().getInt("random.Z"));
                } while ((randomX < this.core.getRegionConfiguration().getInt("location-radius." + i + ".location.x")
                || (randomY < this.core.getRegionConfiguration().getInt("location-radius." + i + ".location.y"))
                || (randomZ < this.core.getRegionConfiguration().getInt("location-radius." + i + ".location.z"))));
            }
        }

        for (final String i : Objects.requireNonNull(this.core.getRegionConfiguration().getConfigurationSection("blacklisted-locations")).getKeys(false)) {
            Location first = new Location(Bukkit.getWorld(Objects.requireNonNull(this.core.getRegionConfiguration().getString("blacklisted-locations." + i + ".location-1.world"))),
                    this.core.getConfig().getInt("blacklisted-locations." + i + ".location-1.x"),
                    this.core.getConfig().getInt("blacklisted-locations." + i + ".location-1.y"),
                    this.core.getConfig().getInt("blacklisted-locations." + i + ".location-1.z"));

            Location second = new Location(Bukkit.getWorld(Objects.requireNonNull(this.core.getRegionConfiguration().getString("blacklisted-locations." + i + ".location-2.world"))),
                    this.core.getConfig().getInt("blacklisted-locations." + i + ".location-2.x"),
                    this.core.getConfig().getInt("blacklisted-locations." + i + ".location-2.y"),
                    this.core.getConfig().getInt("blacklisted-locations." + i + ".location-2.z"));

            if (randomX > first.getX() && randomZ > first.getZ() && randomX < second.getX() && randomZ < second.getZ()) {
                do {
                    randomX = random.nextInt(this.core.getRegionConfiguration().getInt("random.x"));
                    randomY = random.nextInt(this.core.getRegionConfiguration().getInt("random.y"));
                    randomZ = random.nextInt(this.core.getRegionConfiguration().getInt("random.Z"));
                } while (randomX > first.getX() && randomZ > first.getZ() && randomX < second.getX() && randomZ < second.getZ());
            }
        }

        assert world != null;
        return new Location(Bukkit.getWorld(world), randomX, randomY, randomZ);
    }

    private Location bossSpawnLocation(){
        String world = this.core.getRegionConfiguration().getString("spawn.world");
        int randomX = random.nextInt(this.core.getRegionConfiguration().getInt("spawn.x"));
        int randomY = random.nextInt(this.core.getRegionConfiguration().getInt("spawn.y"));
        int randomZ = random.nextInt(this.core.getRegionConfiguration().getInt("spawn.Z"));
        assert world != null;
        return new Location(Bukkit.getWorld(world), randomX, randomY, randomZ);
    }
}
