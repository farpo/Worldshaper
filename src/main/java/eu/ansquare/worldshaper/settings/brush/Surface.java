package eu.ansquare.worldshaper.settings.brush;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Surface {
    public void place(Player player, ItemStack item){
        player.sendMessage("Surface");
    }
}
