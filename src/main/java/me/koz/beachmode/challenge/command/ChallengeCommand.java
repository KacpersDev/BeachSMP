package me.koz.beachmode.challenge.command;

import me.koz.beachmode.Core;
import me.koz.beachmode.challenge.Challenge;
import me.koz.beachmode.challenge.ChallengePlayer;
import me.koz.beachmode.challenge.ChallengeTask;
import me.koz.beachmode.challenge.ChallengeType;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class ChallengeCommand implements CommandExecutor {

    private final ChallengeTask challengeTask = new ChallengeTask();
    private final ChallengePlayer challengePlayer = new ChallengePlayer();

    private List<ChallengeType> challengeTypes;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        Inventory inventory = Bukkit.createInventory(player,
                Core.getInstance().getChallengeConfiguration().getInt("inventory.size"),
                CC.translate(Core.getInstance().getChallengeConfiguration().getString("inventory.title")));

        challengeTypes = ChallengeTask.activeEvents;
        List<String> challenges = new ArrayList<>();
        for (int i = 0; i < challengeTypes.size(); i++) {
            challenges.add(challengeTypes.get(i).toString().toUpperCase());
        }



        item0(player, inventory, challenges);
        item1(player, inventory, challenges);
        item2(player, inventory, challenges);

        player.openInventory(inventory);
        Challenge.inventories.add(player.getUniqueId());
        return true;
    }

    private void item0(Player sender, Inventory inventory, List<String> challenges) {

        HashMap<UUID, Integer> map = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(0).toString().toUpperCase()));
        String first;
        String second;
        String third;
        int firstValue;
        int secondValue;
        int thirdValue;

        List<UUID> keys = map.entrySet().stream().sorted(Map.Entry.<UUID, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

        if (keys.size() < 1 || keys.get(0) == null) {
            first = "None";
            firstValue = 0;
        } else {
            first = Bukkit.getPlayer(keys.get(0)).getName();
            firstValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(0).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(0)).getUniqueId());
        }

        if (keys.size() < 2 || keys.get(1) == null) {
            second = "None";
            secondValue = 0;
        } else {
            second = Bukkit.getPlayer(keys.get(1)).getName();
            secondValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(0).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(1)).getUniqueId());
        }

        if (keys.size() < 3 || keys.get(2) == null) {
            third = "None";
            thirdValue = 0;
        } else {
            third = Bukkit.getPlayer(keys.get(2)).getName();
            thirdValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(0).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(2)).getUniqueId());
        }


        ItemStack itemStack = new ItemStack(Material.valueOf(Core.getInstance().getChallengeConfiguration().getString("challenge." + challenges.get(0) + ".item")));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(CC.translate(Core.getInstance().getChallengeConfiguration().getString("challenge." + challenges.get(0) + ".name")));
        ArrayList<String> lore = new ArrayList<>();
        for (String l : Core.getInstance().getChallengeConfiguration().getStringList("challenge." + challenges.get(0) + ".lore")) {
            lore.add(CC.translate(l)
                    .replace("%timer_hours%", String.valueOf(ChallengeTask.currentTime / 3600))
                    .replace("%timer_minutes%", String.valueOf((ChallengeTask.currentTime % 3600) / 60))
                    .replace("%timer_seconds%", String.valueOf(ChallengeTask.currentTime % 60))
                    .replace("%user_1%", first)
                    .replace("%user_2%", second)
                    .replace("%user_3%", third)
                    .replace("%value_1%", String.valueOf(firstValue))
                    .replace("%value_2%", String.valueOf(secondValue))
                    .replace("%value_3%", String.valueOf(thirdValue))
                    .replace("%you%", sender.getName())
                    .replace("%value_you%", String.valueOf(map.get(sender.getUniqueId()))));
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        inventory.setItem(0, itemStack);
    }

    private void item1(Player sender, Inventory inventory, List<String> challenges) {

        HashMap<UUID, Integer> map = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(1).toString().toUpperCase()));
        String first;
        String second;
        String third;
        int firstValue;
        int secondValue;
        int thirdValue;

        List<UUID> keys = map.entrySet().stream().sorted(Map.Entry.<UUID, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

        if (keys.size() < 1 || keys.get(0) == null) {
            first = "None";
            firstValue = 0;
        } else {
            first = Bukkit.getPlayer(keys.get(0)).getName();
            firstValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(1).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(0)).getUniqueId());
        }

        if (keys.size() < 2 || keys.get(1) == null) {
            second = "None";
            secondValue = 0;
        } else {
            second = Bukkit.getPlayer(keys.get(1)).getName();
            secondValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(1).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(1)).getUniqueId());
        }

        if (keys.size() < 3 || keys.get(2) == null) {
            third = "None";
            thirdValue = 0;
        } else {
            third = Bukkit.getPlayer(keys.get(2)).getName();
            thirdValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(1).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(2)).getUniqueId());
        }

        ItemStack itemStack = new ItemStack(Material.valueOf(Core.getInstance().getChallengeConfiguration().getString("challenge." + challenges.get(1) + ".item")));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(CC.translate(Core.getInstance().getChallengeConfiguration().getString("challenge." + challenges.get(1) + ".name")));
        ArrayList<String> lore = new ArrayList<>();
        for (String l : Core.getInstance().getChallengeConfiguration().getStringList("challenge." + challenges.get(0) + ".lore")) {
            lore.add(CC.translate(l)
                    .replace("%timer_hours%", String.valueOf(ChallengeTask.currentTime / 3600))
                    .replace("%timer_minutes%", String.valueOf((ChallengeTask.currentTime % 3600) / 60))
                    .replace("%timer_seconds%", String.valueOf(ChallengeTask.currentTime % 60))
                    .replace("%user_1%", first)
                    .replace("%user_2%", second)
                    .replace("%user_3%", third)
                    .replace("%value_1%", String.valueOf(firstValue))
                    .replace("%value_2%", String.valueOf(secondValue))
                    .replace("%value_3%", String.valueOf(thirdValue))
                    .replace("%you%", sender.getName())
                    .replace("%value_you%", String.valueOf(map.get(sender.getUniqueId()))));
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        inventory.setItem(4, itemStack);
    }

    private void item2(Player sender, Inventory inventory, List<String> challenges) {

        HashMap<UUID, Integer> map = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(2).toString().toUpperCase()));
        String first;
        String second;
        String third;
        int firstValue;
        int secondValue;
        int thirdValue;

        List<UUID> keys = map.entrySet().stream().sorted(Map.Entry.<UUID, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());

        if (keys.size() < 1 || keys.get(0) == null) {
            first = "None";
            firstValue = 0;
        } else {
            first = Bukkit.getPlayer(keys.get(0)).getName();
            firstValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(2).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(0)).getUniqueId());
        }

        if (keys.size() < 2 || keys.get(1) == null) {
            second = "None";
            secondValue = 0;
        } else {
            second = Bukkit.getPlayer(keys.get(1)).getName();
            secondValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(2).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(1)).getUniqueId());
        }

        if (keys.size() < 3 || keys.get(2) == null) {
            third = "None";
            thirdValue = 0;
        } else {
            third = Bukkit.getPlayer(keys.get(2)).getName();
            thirdValue = challengePlayer.getMapByEnum(ChallengeType.valueOf(challengeTypes.get(2).toString().toUpperCase())).get(Bukkit.getPlayer(keys.get(2)).getUniqueId());
        }

        ItemStack itemStack = new ItemStack(Material.valueOf(Core.getInstance().getChallengeConfiguration().getString("challenge." + challenges.get(2) + ".item")));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(CC.translate(Core.getInstance().getChallengeConfiguration().getString("challenge." + challenges.get(2) + ".name")));
        ArrayList<String> lore = new ArrayList<>();
        for (String l : Core.getInstance().getChallengeConfiguration().getStringList("challenge." + challenges.get(0) + ".lore")) {
            lore.add(CC.translate(l)
                    .replace("%timer_hours%", String.valueOf(ChallengeTask.currentTime / 3600))
                    .replace("%timer_minutes%", String.valueOf((ChallengeTask.currentTime % 3600) / 60))
                    .replace("%timer_seconds%", String.valueOf(ChallengeTask.currentTime % 60))
                    .replace("%user_1%", first)
                    .replace("%user_2%", second)
                    .replace("%user_3%", third)
                    .replace("%value_1%", String.valueOf(firstValue))
                    .replace("%value_2%", String.valueOf(secondValue))
                    .replace("%value_3%", String.valueOf(thirdValue))
                    .replace("%you%", sender.getName())
                    .replace("%value_you%", String.valueOf(map.get(sender.getUniqueId()))));
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        inventory.setItem(8, itemStack);
    }
}
