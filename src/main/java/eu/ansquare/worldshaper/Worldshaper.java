package eu.ansquare.worldshaper;

import eu.ansquare.worldshaper.itemmanager.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Worldshaper extends JavaPlugin {
    static Plugin thisPlugin;
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
        thisPlugin = this;
    }
    public static Plugin getPlugin(){
        return thisPlugin;
    }

}