package me.koz.beachmode.gold;

import me.clip.placeholderapi.PlaceholderHook;
import me.koz.beachmode.Core;
import org.bukkit.entity.Player;

public class GoldPlaceHolder extends PlaceholderHook {

    private Gold gold;

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        this.gold = new Gold(Core.getInstance(), p);

        if (identifier.equals("gold_amount")) {
            return String.valueOf(gold.getGold());
        }
        return null;
    }
}
