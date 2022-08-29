package me.koz.beachmode.randomteleport;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.utils.CC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

@Getter
public class RandomTP {

    private final Core core;
    private final Random random = new Random();

    public RandomTP(Core core) {
        this.core = core;
    }

    public Location getHighestBock(Player player, World world, int x, int z){
        player.sendMessage(CC.translate(this.core.getTeleportConfiguration().getString("check")));
        int i = 255;
        while(i>0){
            if(new Location(world, x, i, z).getBlock().getType()!= Material.AIR
            && (new Location(world,x,i,z).getBlock().getType() != Material.WATER)
            && (new Location(world,x,i,z).getBlock().getType() != Material.LAVA)) {
                return new Location(world, x, i, z).add(0,1,0);
            }
            i--;

        }
        return new Location(world, x, 1, z);
    }
}
