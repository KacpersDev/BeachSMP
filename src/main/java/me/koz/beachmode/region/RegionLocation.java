package me.koz.beachmode.region;

import lombok.Getter;
import org.bukkit.Location;

@Getter
public class RegionLocation {

    private Location location, location1, location2;

    public RegionLocation(Location location) {
        this.location = location;
    }

    public RegionLocation(Location location1, Location location2) {
        this.location1 = location1;
        this.location2 = location2;
    }


}
