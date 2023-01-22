package eu.ansquare.worldshaper;

import eu.ansquare.worldshaper.gui.GuiManager;
import eu.ansquare.worldshaper.itemmanager.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;

public class OperationListener implements Listener {
    GuiManager gm = new GuiManager();
    ItemManager im = new ItemManager();

    long lastInteractionTime =0L;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack item = player.getInventory().getItemInMainHand();
        if (im.isWorldshaper(item)) {
            OperationManager om = new OperationManager();
            long now = System.currentTimeMillis();
            if (event.getAction().isRightClick() && (now - lastInteractionTime) > 100 && !player.isSneaking()) {

                    om.findTargetBlock(event.getPlayer(), 64, false, item);
                    lastInteractionTime = System.currentTimeMillis();

            } else if (event.getAction().isLeftClick()) {
                om.findTargetBlock(player, 64, true, item);
                event.setCancelled(true);
            }
        }
    }
}