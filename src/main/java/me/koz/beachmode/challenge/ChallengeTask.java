package me.koz.beachmode.challenge;

import me.koz.beachmode.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ChallengeTask extends BukkitRunnable {

    public static ArrayList<ChallengeType> activeEvents = new ArrayList<>();
    private int timer = Core.getInstance()
            .getChallengeConfiguration()
            .getInt("challenge-timer");

    public static int currentTime;
    private final Challenge challenge = new Challenge();

    @Override
    public void run() {
        if (timer == Core.getInstance().getChallengeConfiguration().getInt("challenge-timer")) {
            challenge.start();
        }
        timer--;

        currentTime = timer;
        if (timer <= 1) {
           challenge.reward1();
           challenge.reward2();
           challenge.reward3();
           challenge.stop();
           timer = Core.getInstance().getChallengeConfiguration().getInt("challenge-timer");
        }
    }

    public static boolean isActiveEvent(ChallengeType challengeType){
        return activeEvents.contains(challengeType);
    }
}
