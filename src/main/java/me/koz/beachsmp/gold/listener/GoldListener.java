package me.koz.beachsmp.gold.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import me.koz.beachsmp.Core;
import me.koz.beachsmp.gold.Gold;
import me.koz.beachsmp.gold.GoldInventory;
import me.koz.beachsmp.gold.GoldPlaceHolder;
import me.koz.beachsmp.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

@SuppressWarnings("ALL")
public class GoldListener implements Listener {

    private final Core core;
    private Gold gold;
    private final GoldInventory goldInventory;
    public GoldListener(Core core) {
        this.core = core;
        this.goldInventory = new GoldInventory(this.core);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        this.gold = new Gold(this.core, event.getPlayer());
        if (!this.gold.playerExists()) {
            this.gold.createPlayer();
            PlaceholderAPI.registerPlaceholderHook(Core.getInstance(), new GoldPlaceHolder());
        }
    }


    @EventHandler
    public void onInventory(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();
        this.gold = new Gold(this.core, player);
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig()
                .getString("category.title")))) {
            event.setCancelled(true);
        }

        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) {
            return;
        } else {
            event.getCurrentItem().getItemMeta().getDisplayName();
        }
        if (Objects.requireNonNull(Objects.requireNonNull
                (event.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("ranks.open")))) {
            player.closeInventory();
            this.goldInventory.openRanks(player);
        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("crates.open")))) {
            player.closeInventory();
            this.goldInventory.openCrates(player);
        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("perks.open")))) {
            player.closeInventory();
            this.goldInventory.openPerks(player);
        } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("misc.open")))) {
            player.closeInventory();
            this.goldInventory.openMisc(player);
        }
    }

    @EventHandler
    public void onInventoryRanks(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        this.gold = new Gold(this.core, player);

        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("ranks.title")))) {
            event.setCancelled(true);
        }

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("ranks.items")).getKeys(false)) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
            if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase(CC.translate(this.core
                    .getConfig().getString("ranks.items." + i + ".name")))) {
                int price = this.core.getConfig().getInt("ranks.items." + i + ".price");
                if (this.gold.getGold() >= price) {
                    this.gold.removeGold(price);
                    if (this.core.getConfig().getString("ranks.items." + i + ".command") == null) return;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(this.core.getConfig().getString("ranks.items." + i + ".command"))
                            .replace("%player%", player.getName())));
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("purchased"))));
                } else {
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("not-enough-money"))
                            .replace("%player%", player.getName())
                            .replace("%needed%", String.valueOf(price))
                            .replace("%player_money%", String.valueOf(this.gold.getGold()))));
                }
            }
        }
    }

    @EventHandler
    public void onInventoryCrates(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        this.gold = new Gold(this.core, player);
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("crates.title")))) {
            event.setCancelled(true);
        }

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("crates.items")).getKeys(false)) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
            if (Objects.requireNonNull(event.getCurrentItem()).getItemMeta() == null || event.getCurrentItem() == null) return;
            if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase(CC.translate(this.core
                    .getConfig().getString("crates.items." + i + ".name")))) {
                int price = this.core.getConfig().getInt("crates.items." + i + ".price");
                if (this.gold.getGold() >= price) {
                    this.gold.removeGold(price);
                    if (this.core.getConfig().getString("ranks.items." + i + ".command") == null) return;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(this.core.getConfig().getString("crates.items." + i + ".command"))
                            .replace("%player%", player.getName())));
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("purchased"))));
                } else {
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("not-enough-money"))
                            .replace("%player%", player.getName())
                            .replace("%needed%", String.valueOf(price))
                            .replace("%player_money%", String.valueOf(this.gold.getGold()))));
                }
            }
        }
    }

    @EventHandler
    public void onInventoryPerks(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        this.gold = new Gold(this.core, player);
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("perks.title")))) {
            event.setCancelled(true);
        }

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("perks.items")).getKeys(false)) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
            if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase(CC.translate(this.core
                    .getConfig().getString("perks.items." + i + ".name")))) {
                int price = this.core.getConfig().getInt("perks.items." + i + ".price");
                if (this.gold.getGold() >= price) {
                    this.gold.removeGold(price);
                    if (this.core.getConfig().getString("ranks.items." + i + ".command") == null) return;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(this.core.getConfig().getString("perks.items." + i + ".command"))
                            .replace("%player%", player.getName())));
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("purchased"))));
                } else {
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("not-enough-money"))
                            .replace("%player%", player.getName())
                            .replace("%needed%", String.valueOf(price))
                            .replace("%player_money%", String.valueOf(this.gold.getGold()))));
                }
            }
        }
    }

    @EventHandler
    public void onInventoryMisc(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        this.gold = new Gold(this.core, player);
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig().getString("misc.title")))) {
            event.setCancelled(true);
        }

        for (final String i : Objects.requireNonNull(this.core.getConfig().getConfigurationSection("misc.items")).getKeys(false)) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
            if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).getDisplayName().equalsIgnoreCase(CC.translate(this.core
                    .getConfig().getString("misc.items." + i + ".name")))) {
                int price = this.core.getConfig().getInt("misc.items." + i + ".price");
                if (this.gold.getGold() >= price) {
                    this.gold.removeGold(price);
                    if (this.core.getConfig().getString("ranks.items." + i + ".command") == null) return;
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(this.core.getConfig().getString("misc.items." + i + ".command"))
                            .replace("%player%", player.getName())));
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("purchased"))));
                } else {
                    player.sendMessage(CC.translate(Objects.requireNonNull(this.core.getConfig().getString("not-enough-money"))
                            .replace("%player%", player.getName())
                            .replace("%needed%", String.valueOf(price))
                            .replace("%player_money%", String.valueOf(this.gold.getGold()))));
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig()
                .getString("ranks.title")))
        || event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig()
                .getString("perks.title")))
        || event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig()
                .getString("misc.title")))
        || event.getView().getTitle().equalsIgnoreCase(CC.translate(this.core.getConfig()
                .getString("crates.title")))) {
            new BukkitRunnable(){ // to avoid any bugs with too fast opening

                @Override
                public void run() {
                    goldInventory.openCategory(player);
                }
            }.runTaskLater(Core.getInstance(), 5L);
        }
    }
}
