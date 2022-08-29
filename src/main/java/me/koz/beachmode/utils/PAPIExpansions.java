package me.koz.beachmode.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.koz.beachmode.Core;
import me.koz.beachmode.gold.Gold;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PAPIExpansions extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "beach";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Koz";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer p, String params) {
        if(params.equalsIgnoreCase("gold")){
            Gold gold = new Gold(Core.getInstance(), p);
            try {
                return String.valueOf(gold.getGold());
            } catch (Exception e){
                return "";
            }
        }
        return null;
    }
}