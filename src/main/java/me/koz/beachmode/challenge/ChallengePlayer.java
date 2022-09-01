package me.koz.beachmode.challenge;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class ChallengePlayer {

    public static HashMap<UUID, Integer> diamondMined = new HashMap<>();
    public static HashMap<UUID, Integer> emeraldMined = new HashMap<>();
    public static HashMap<UUID, Integer> ironMined = new HashMap<>();
    public static HashMap<UUID, Integer> goldMined = new HashMap<>();
    public static HashMap<UUID, Integer> skeletonKilled = new HashMap<>();
    public static HashMap<UUID, Integer> zombieKilled = new HashMap<>();
    public static HashMap<UUID, Integer> cowKilled = new HashMap<>();
    public static HashMap<UUID, Integer> blockMined = new HashMap<>();
    public static HashMap<UUID, Integer> playerKilled = new HashMap<>();
    public static HashMap<UUID, Integer> blockPlaced = new HashMap<>();
    private Player player;

    public ChallengePlayer(Player player) {
        this.player = player;
    }

    public ChallengePlayer() {

    }

    public HashMap<UUID, Integer> getMapByEnum(ChallengeType enumClass){
        if (enumClass.equals(ChallengeType.BLOCK_PLACED)) {
            return blockPlaced;
        } else if (enumClass.equals(ChallengeType.DIAMOND_MINED)) {
            return diamondMined;
        } else if (enumClass.equals(ChallengeType.EMERALD_MINED)) {
            return emeraldMined;
        } else if (enumClass.equals(ChallengeType.IRON_MINED)) {
            return ironMined;
        } else if (enumClass.equals(ChallengeType.GOLD_MINED)) {
            return goldMined;
        } else if (enumClass.equals(ChallengeType.SKELETON_KILLED)) {
            return skeletonKilled;
        } else if (enumClass.equals(ChallengeType.ZOMBIE_KILLED)) {
            return zombieKilled;
        } else if (enumClass.equals(ChallengeType.COW_KILLED)) {
            return cowKilled;
        } else if (enumClass.equals(ChallengeType.BLOCK_MINED)) {
            return blockMined;
        } else if (enumClass.equals(ChallengeType.PLAYER_KILLED)) {
            return playerKilled;
        }
        return null;
    }

    public void add(ChallengeType challengeType) {

        if (challengeType.equals(ChallengeType.BLOCK_MINED)) {
            if (!blockMined.containsKey(player.getUniqueId())) {
                blockMined.put(player.getUniqueId(), 1);
            } else {
                blockMined.replace(player.getUniqueId(), blockMined.get(player.getUniqueId()), blockMined.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.DIAMOND_MINED)) {
            if (!diamondMined.containsKey(player.getUniqueId())) {
                diamondMined.put(player.getUniqueId(), 1);
            } else {
                diamondMined.replace(player.getUniqueId(), diamondMined.get(player.getUniqueId()), diamondMined.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.EMERALD_MINED)) {
            if (!emeraldMined.containsKey(player.getUniqueId())) {
                emeraldMined.put(player.getUniqueId(), 1);
            } else {
                emeraldMined.replace(player.getUniqueId(), emeraldMined.get(player.getUniqueId()), emeraldMined.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.IRON_MINED)) {
            if (!ironMined.containsKey(player.getUniqueId())) {
                ironMined.put(player.getUniqueId(), 1);
            } else {
                ironMined.replace(player.getUniqueId(), ironMined.get(player.getUniqueId()), ironMined.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.GOLD_MINED)) {
            if (!goldMined.containsKey(player.getUniqueId())) {
                goldMined.put(player.getUniqueId(), 1);
            } else {
                goldMined.replace(player.getUniqueId(), goldMined.get(player.getUniqueId()), goldMined.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.SKELETON_KILLED)) {
            if (!skeletonKilled.containsKey(player.getUniqueId())) {
                skeletonKilled.put(player.getUniqueId(), 1);
            } else {
                skeletonKilled.replace(player.getUniqueId(), skeletonKilled.get(player.getUniqueId()), skeletonKilled.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.ZOMBIE_KILLED)) {
            if (!zombieKilled.containsKey(player.getUniqueId())) {
                zombieKilled.put(player.getUniqueId(), 1);
            } else {
                zombieKilled.replace(player.getUniqueId(), zombieKilled.get(player.getUniqueId()), zombieKilled.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.COW_KILLED)) {
            if (!cowKilled.containsKey(player.getUniqueId())) {
                cowKilled.put(player.getUniqueId(), 1);
            } else {
                cowKilled.replace(player.getUniqueId(), cowKilled.get(player.getUniqueId()), cowKilled.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.PLAYER_KILLED)) {
            if (!playerKilled.containsKey(player.getUniqueId())) {
                playerKilled.put(player.getUniqueId(), 1);
            } else {
                playerKilled.replace(player.getUniqueId(), playerKilled.get(player.getUniqueId()), playerKilled.get(player.getUniqueId()) + 1);
            }
        } else if (challengeType.equals(ChallengeType.BLOCK_PLACED)) {
            if (!blockPlaced.containsKey(player.getUniqueId())) {
                blockPlaced.put(player.getUniqueId(), 1);
            } else {
                blockPlaced.replace(player.getUniqueId(), blockPlaced.get(player.getUniqueId()), blockPlaced.get(player.getUniqueId()) + 1);
            }
        }
    }

    public void clear() {
        playerKilled.clear();
        diamondMined.clear();
        emeraldMined.clear();
        ironMined.clear();
        goldMined.clear();
        skeletonKilled.clear();
        zombieKilled.clear();
        cowKilled.clear();
        blockPlaced.clear();
        blockMined.clear();
    }
}
