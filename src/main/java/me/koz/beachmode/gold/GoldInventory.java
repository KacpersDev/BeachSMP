package me.koz.beachmode.gold;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

@Getter
public class GoldInventory {

    private final Core core;
    private Gold gold;
    private final Inventory ranksInventory, crateInventory, perksInventory, miscInventory, categoryInventory;

    public GoldInventory(Core core) {
        this.core = core;
        ranksInventory = Bukkit.createInventory(null, this.core.getConfig()
                .getInt("ranks.size"), CC.translate(this.core.getConfig()
                .getString("ranks.title")));
        crateInventory = Bukkit.createInventory(null, this.core.getConfig().getInt("crates.size")
        ,CC.translate(this.core.getConfig().getString("crates.title")));
        perksInventory = Bukkit.createInventory(null, this.core.getConfig().getInt("perks.size"),
                CC.translate(this.core.getConfig().getString("perks.title")));
        miscInventory = Bukkit.createInventory(null, this.core.getConfig().getInt("misc.size"),
                CC.translate(this.core.getConfig().getString("misc.title")));
        categoryInventory = Bukkit.createInventory(null, Core.getInstance().getConfig()
                .getInt("category.size"), CC.translate(Core.getInstance().getConfig()
                .getString("category.title")));
    }

    public void openRanks(Player player){

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("ranks.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.core.getConfig().getString("ranks.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.core.getConfig().getString("ranks.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.core.getConfig().getStringList("ranks.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            ranksInventory.setItem(this.core.getConfig().getInt("ranks.items." + i + ".slot"), item);
        }

        player.openInventory(ranksInventory);
    }

    public void openCrates(Player player) {

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("crates.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.core.getConfig().getString("crates.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.core.getConfig().getString("crates.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.core.getConfig().getStringList("crates.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            crateInventory.setItem(this.core.getConfig().getInt("crates.items." + i + ".slot"), item);
        }
        player.openInventory(crateInventory);
    }

    public void openPerks(Player player) {

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("perks.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.core.getConfig().getString("perks.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.core.getConfig().getString("perks.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.core.getConfig().getStringList("perks.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            perksInventory.setItem(this.core.getConfig().getInt("perks.items." + i + ".slot"), item);
        }

        player.openInventory(perksInventory);
    }

    public void openMisc(Player player) {

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("misc.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.core.getConfig().getString("misc.items." + i + ".item")));
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(this.core.getConfig().getString("misc.items." + i + ".name")));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.core.getConfig().getStringList("misc.items." + i + ".lore")) {
                lore.add(CC.translate(l));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            miscInventory.setItem(this.core.getConfig().getInt("perks.items." + i + ".slot"), item);
        }

        player.openInventory(miscInventory);
    }

    public void openCategory(Player player){
        this.gold = new Gold(this.core, player);

        for (final String i : Objects.requireNonNull(core.getConfig()
                .getConfigurationSection("category.items")).getKeys(false)) {
            ItemStack item = new ItemStack(Material.valueOf(this.core.getConfig().getString("category.items." + i + ".item")), 1);
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            meta.setDisplayName(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("category.items." + i + ".name"))
                    .replace("%player%", player.getName())
                    .replace("%gold%", String.valueOf(gold.getGold()))));
            ArrayList<String> lore = new ArrayList<>();
            for (final String l : this.core.getConfig().getStringList("category.items." + i + ".lore")) {
                lore.add(CC.translate(l
                        .replace("%player%", player.getName())
                        .replace("%gold%", String.valueOf(gold.getGold()))));
            }
            meta.setLore(lore);
            item.setItemMeta(meta);
            categoryInventory.setItem(core.getConfig().getInt("category.items." + i + ".slot"), item);
        }
        player.openInventory(categoryInventory);
    }
}
