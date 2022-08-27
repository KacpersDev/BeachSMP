package me.koz.beachsmp.combat;

import lombok.Getter;
import me.koz.beachsmp.Core;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class Combat {

    private final Core core;

    public Combat(Core core) {
        this.core = core;
    }

    public static HashMap<UUID, Long> combat = new HashMap<>();
    public static HashMap<UUID, UUID> entitiesList = new HashMap<>();
    public static HashMap<UUID, LivingEntity> entities = new HashMap<>();
    public static HashMap<UUID, UUID> players = new HashMap<>();
    public static HashMap<UUID, List<ItemStack>> items = new HashMap<>();


    public void updateCooldown(Player damager, Player player){

        if (combat.containsKey(damager.getUniqueId()) && combat.get(damager.getUniqueId()) > System.currentTimeMillis()) {
            replaceCooldown(damager);
        }

        if (combat.containsKey(player.getUniqueId()) && combat.get(player.getUniqueId()) > System.currentTimeMillis()) {
            replaceCooldown(player);
        }

        if (!combat.containsKey(damager.getUniqueId()) && !combat.containsKey(player.getUniqueId())) {
            putCooldown(damager, player);
        } else if (!combat.containsKey(damager.getUniqueId()) && combat.containsKey(player.getUniqueId())) {
            replaceCooldown(player);
            putPlayer(damager);
        } else if (combat.containsKey(damager.getUniqueId()) && !combat.containsKey(player.getUniqueId())) {
            replaceCooldown(damager);
            putPlayer(player);
        }
    }

    public void replaceCooldown(Player player){
        combat.replace(player.getUniqueId(), combat.get(player.getUniqueId()),System.currentTimeMillis() + (this.core.getCombatConfiguration().getInt("time")) * 1000L);
    }

    public void putCooldown(Player damager, Player player){
        combat.put(damager.getUniqueId(), System.currentTimeMillis() + (this.core.getCombatConfiguration().getInt("time")) * 1000L);
        combat.put(player.getUniqueId(), System.currentTimeMillis() + (this.core.getCombatConfiguration().getInt("time")) * 1000L);
    }

    public void putPlayer(Player player){
        combat.put(player.getUniqueId(), System.currentTimeMillis() + (this.core.getCombatConfiguration().getInt("time")) * 1000L);
    }
}
