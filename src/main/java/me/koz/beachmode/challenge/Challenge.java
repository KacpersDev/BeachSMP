package me.koz.beachmode.challenge;

import lombok.Getter;
import me.koz.beachmode.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
public class Challenge {

    private final ChallengePlayer challengePlayer = new ChallengePlayer();
    private final ChallengeRandomizer randomizer = new ChallengeRandomizer();

    public static ArrayList<UUID> inventories = new ArrayList<>();
    public static boolean started = false;

    public void start(){
        started = true;
        List<ChallengeType> challengeTypes = randomizer.randomize();
        ChallengeTask.activeEvents.add(challengeTypes.get(0));
        ChallengeTask.activeEvents.add(challengeTypes.get(1));
        ChallengeTask.activeEvents.add(challengeTypes.get(2));
    }

    public void stop(){
        started = false;
        challengePlayer.clear();
        ChallengeTask.activeEvents.clear();
        List<ChallengeType> challengeTypes = randomizer.randomize();
        ChallengeTask.activeEvents.add(challengeTypes.get(0));
        ChallengeTask.activeEvents.add(challengeTypes.get(1));
        ChallengeTask.activeEvents.add(challengeTypes.get(2));
    }

    public void reward1() {
        ChallengeType challengeType = ChallengeTask.activeEvents.get(0);
        if (challengeType.equals(ChallengeType.BLOCK_PLACED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.blockPlaced.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.blockPlaced.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.BLOCK_PLACED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.blockPlaced.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.BLOCK_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.blockMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.blockMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.BLOCK_MINED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.blockMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.COW_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.cowKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.cowKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.COW_KILLED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.cowKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.ZOMBIE_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.zombieKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.zombieKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.ZOMBIE_KILLED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.zombieKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.SKELETON_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.skeletonKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.skeletonKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.SKELETON_KILLED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.skeletonKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.GOLD_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.goldMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.goldMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.GOLD_MINED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.goldMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.IRON_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.ironMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.ironMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.IRON_MINED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.ironMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.EMERALD_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.emeraldMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.emeraldMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.EMERALD_MINED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.emeraldMined.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.DIAMOND_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.diamondMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.diamondMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.DIAMOND_MINED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.diamondMined.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.PLAYER_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 1) return;
            int maxValue = (Collections.max(challengePlayer.playerKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.playerKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.PLAYER_KILLED.rewards1.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.playerKilled.remove(player.getUniqueId());
                    }
                }
            }
        }
    }
    public void reward2() {


        ChallengeType challengeType = ChallengeTask.activeEvents.get(1);
        if (challengeType.equals(ChallengeType.BLOCK_PLACED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            int maxValue = (Collections.max(challengePlayer.blockPlaced.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.blockPlaced.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.BLOCK_PLACED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.blockPlaced.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.BLOCK_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            if (Collections.max(challengePlayer.blockMined.values()) == null) return;
            int maxValue = (Collections.max(challengePlayer.blockMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.blockMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.BLOCK_MINED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.blockMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.COW_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            int maxValue = (Collections.max(challengePlayer.cowKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.cowKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.COW_KILLED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.cowKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.ZOMBIE_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            int maxValue = (Collections.max(challengePlayer.zombieKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.zombieKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.ZOMBIE_KILLED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.zombieKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.SKELETON_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            int maxValue = (Collections.max(challengePlayer.skeletonKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.skeletonKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.SKELETON_KILLED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.skeletonKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.GOLD_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            if (Collections.max(challengePlayer.goldMined.values()) == null) return;
            int maxValue = (Collections.max(challengePlayer.goldMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.goldMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.GOLD_MINED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.goldMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.IRON_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            int maxValue = (Collections.max(challengePlayer.ironMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.ironMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.IRON_MINED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.ironMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.EMERALD_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            int maxValue = (Collections.max(challengePlayer.emeraldMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.emeraldMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.EMERALD_MINED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.emeraldMined.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.DIAMOND_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            if (Collections.max(challengePlayer.diamondMined.values()) == null) return;
            int maxValue = (Collections.max(challengePlayer.diamondMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.diamondMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.DIAMOND_MINED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.diamondMined.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.PLAYER_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 2) return;
            if (Collections.max(challengePlayer.playerKilled.values()) == null) return;
            int maxValue = (Collections.max(challengePlayer.playerKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.playerKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.PLAYER_KILLED.rewards2.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.playerKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

    }

    public void reward3() {


        ChallengeType challengeType = ChallengeTask.activeEvents.get(2);
        if (challengeType.equals(ChallengeType.BLOCK_PLACED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.blockPlaced.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.blockPlaced.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.BLOCK_PLACED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.blockPlaced.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.BLOCK_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.blockMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.blockMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.BLOCK_MINED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.blockMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.COW_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            if (Collections.max(challengePlayer.cowKilled.values()) == null) return;
            int maxValue = (Collections.max(challengePlayer.cowKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.cowKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.COW_KILLED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.cowKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.ZOMBIE_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.zombieKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.zombieKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.ZOMBIE_KILLED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.zombieKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.SKELETON_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.skeletonKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.skeletonKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.SKELETON_KILLED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.skeletonKilled.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.GOLD_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.goldMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.goldMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.GOLD_MINED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.goldMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.IRON_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.ironMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.ironMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.IRON_MINED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.ironMined.remove(player.getUniqueId());
                    }
                }
            }
        }

        if (challengeType.equals(ChallengeType.EMERALD_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.emeraldMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.emeraldMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.EMERALD_MINED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.emeraldMined.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.DIAMOND_MINED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.diamondMined.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.diamondMined.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.DIAMOND_MINED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.diamondMined.remove(player.getUniqueId());
                    }
                }
            }
        }
        if (challengeType.equals(ChallengeType.PLAYER_KILLED)) {
            if (ChallengePlayer.blockPlaced.size() < 3) return;
            int maxValue = (Collections.max(challengePlayer.playerKilled.values()));
            for (Map.Entry<UUID, Integer> entry : challengePlayer.playerKilled.entrySet()) {
                if (entry.getValue() == maxValue) {
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (final String i : Core.getInstance().getChallengeConfiguration()
                            .getStringList("challenge.PLAYER_KILLED.rewards3.commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), i.replace("%player%", player.getName()));
                        challengePlayer.playerKilled.remove(player.getUniqueId());
                    }
                }
            }
        }
    }
}