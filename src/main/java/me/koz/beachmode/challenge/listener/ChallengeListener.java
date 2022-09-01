package me.koz.beachmode.challenge.listener;

import me.koz.beachmode.Core;
import me.koz.beachmode.challenge.Challenge;
import me.koz.beachmode.challenge.ChallengePlayer;
import me.koz.beachmode.challenge.ChallengeTask;
import me.koz.beachmode.challenge.ChallengeType;
import me.koz.beachmode.utils.CC;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ChallengeListener implements Listener {

    @EventHandler
    public void onCowDeath(EntityDeathEvent event) {

        if (event.getEntity() == null || event.getEntity().getKiller() == null) return;
        if (!event.getEntity().getType().equals(EntityType.COW)) return;
        if (!ChallengeTask.isActiveEvent(ChallengeType.COW_KILLED)) return;
        Player player = event.getEntity().getKiller();
        new ChallengePlayer(player).add(ChallengeType.COW_KILLED);
    }

    @EventHandler
    public void onSkeletonDeath(EntityDeathEvent event) {

        if (event.getEntity() == null || event.getEntity().getKiller() == null) return;
        if (!event.getEntity().getType().equals(EntityType.SKELETON)) return;
        if (!ChallengeTask.isActiveEvent(ChallengeType.SKELETON_KILLED)) return;
        Player player = event.getEntity().getKiller();
        new ChallengePlayer(player).add(ChallengeType.SKELETON_KILLED);
    }

    @EventHandler
    public void onZombieDeath(EntityDeathEvent event) {

        if (event.getEntity() == null || event.getEntity().getKiller() == null) return;
        if (!event.getEntity().getType().equals(EntityType.ZOMBIE)) return;
        if (!ChallengeTask.isActiveEvent(ChallengeType.ZOMBIE_KILLED)) return;
        Player player = event.getEntity().getKiller();
        new ChallengePlayer(player).add(ChallengeType.ZOMBIE_KILLED);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        if (!ChallengeTask.isActiveEvent(ChallengeType.BLOCK_MINED)) return;
        new ChallengePlayer(player).add(ChallengeType.BLOCK_MINED);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();
        if (!ChallengeTask.isActiveEvent(ChallengeType.BLOCK_PLACED)) return;
        new ChallengePlayer(player).add(ChallengeType.BLOCK_PLACED);
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof Player
        && event.getEntity().getPlayer() != null && event.getEntity().getPlayer() != null) return;
        if (!ChallengeTask.isActiveEvent(ChallengeType.PLAYER_KILLED)) return;
        Player player = event.getEntity().getKiller();
        new ChallengePlayer(player).add(ChallengeType.PLAYER_KILLED);
    }

    @EventHandler
    public void onDiamondMine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!ChallengeTask.isActiveEvent(ChallengeType.DIAMOND_MINED)) return;
        if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
            new ChallengePlayer(player).add(ChallengeType.DIAMOND_MINED);
        }
    }

    @EventHandler
    public void onEmeraldMine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!ChallengeTask.isActiveEvent(ChallengeType.EMERALD_MINED)) return;
        if (event.getBlock().getType().equals(Material.EMERALD_ORE)) {
            new ChallengePlayer(player).add(ChallengeType.EMERALD_MINED);
        }
    }

    @EventHandler
    public void onGoldMine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!ChallengeTask.isActiveEvent(ChallengeType.GOLD_MINED)) return;
        if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
            new ChallengePlayer(player).add(ChallengeType.GOLD_MINED);
        }
    }

    @EventHandler
    public void onIronMine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!ChallengeTask.isActiveEvent(ChallengeType.IRON_MINED)) return;
        if (event.getBlock().getType().equals(Material.IRON_ORE)) {
            new ChallengePlayer(player).add(ChallengeType.IRON_MINED);
        }
    }

    @EventHandler
    public void onTake(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(Core.getInstance()
                .getChallengeConfiguration().getString("inventory.title")))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(Core.getInstance()
                .getChallengeConfiguration().getString("inventory.title")))) {
            Challenge.inventories.remove(event.getPlayer().getUniqueId());
        }
    }
}
