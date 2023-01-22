package eu.ansquare.worldshaper;

import eu.ansquare.worldshaper.gui.GuiManager;
import eu.ansquare.worldshaper.itemmanager.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;

public class Worldshaper extends JavaPlugin implements Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (label.equalsIgnoreCase("worldshaper")){
            if(sender instanceof Player){
                if (args.length>=1){
                    OperationManager om = new OperationManager();
                    ItemManager im = new ItemManager();
                    if(args[0].equalsIgnoreCase("give")){
                        im.giveItemToPlayer((Player) sender);
                        return true;
                    }
                    else if(args[0].equalsIgnoreCase("modify")){
                        im.itemModifyCommand((Player) sender, args);
                        return true;
                    }
                    else {
                        sender.sendMessage("Unknown argument");
                        return true;
                    }
                }
                else {
                    sender.sendMessage("You gotta specify");
                    return true;
                }
            }
            else {
                sender.sendMessage("Only a player can use this command");
                return true;
            }
        }
        return false;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OperationListener(), this);
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemManager im = new ItemManager();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (im.isWorldshaper(item)) {
            if (event.getAction().isRightClick() && player.isSneaking()){
                GuiManager gm = new GuiManager();
                getServer().getPluginManager().registerEvents(gm, this);
                gm.ExampleGui(item, player);
            }
        }
    }
}
