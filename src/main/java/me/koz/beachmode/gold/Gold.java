package me.koz.beachmode.gold;

import lombok.Getter;
import me.koz.beachmode.Core;
import me.koz.beachmode.utils.Config;
import org.bukkit.OfflinePlayer;

@Getter
public class Gold {

    private OfflinePlayer player;
    private final Core core;

    public Gold(Core core) {
        this.core = core;
    }

    public Gold(Core core, OfflinePlayer player) {
        this.core = core;
        this.player = player;
    }

    public boolean playerExists(){
        return this.core.getDataConfiguration().contains("Player." + player.getUniqueId());
    }

    public void createPlayer(){
        this.core.getDataConfiguration().set("Player." + player.getUniqueId() + ".gold", 0);
        new Config(this.core.getData(), this.core.getDataConfiguration());
    }

    public void setGold(int amount){
        this.core.getDataConfiguration().set("Player." + player.getUniqueId() + ".gold", amount);
        new Config(this.core.getData(), this.core.getDataConfiguration());
    }

    public void addGold(int amount) {
        int gold = getGold();
        this.core.getDataConfiguration().set("Player." + player.getUniqueId() + ".gold", (gold + amount));
        new Config(this.core.getData(), this.core.getDataConfiguration());
    }

    public void removeGold(int amount) {
        int gold = getGold();
        if (gold >= amount) {
            this.core.getDataConfiguration().set("Player." + player.getUniqueId() + ".gold", (gold - amount));
        } else {
            setGold(0);
        }
        new Config(this.core.getData(), this.core.getDataConfiguration());
    }

    public int getGold(){
        return this.core.getDataConfiguration().getInt("Player." + player.getUniqueId() + ".gold");
    }
}
