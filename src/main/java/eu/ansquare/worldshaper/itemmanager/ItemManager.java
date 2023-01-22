package eu.ansquare.worldshaper.itemmanager;

import eu.ansquare.worldshaper.settings.AttachType;
import eu.ansquare.worldshaper.settings.BrushType;
import eu.ansquare.worldshaper.settings.ToolType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemManager {
    public void giveItemToPlayer(Player player){
        NamespacedKey key = new NamespacedKey("worldshaper", "is-worldshaper");
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "yes");
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
    }

    public boolean isWorldshaper(ItemStack item) {
        if (item.hasItemMeta()) {
            NamespacedKey key = new NamespacedKey("worldshaper", "is-worldshaper");
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            if (container.has(key, PersistentDataType.STRING)) {
                String valueString = container.get(key, PersistentDataType.STRING);
                if (valueString.equals("yes")) {
                    return true;
                }
                return false;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    public void setPickedBlock(Block block, ItemStack item){
        NamespacedKey key = new NamespacedKey("worldshaper", "picked-block");
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, block.getType().toString());
        item.setItemMeta(meta);
    }
    public Material getPickedBlock(ItemStack item){
        NamespacedKey key = new NamespacedKey("worldshaper", "picked-block");
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)){
            String valueString = container.get(key, PersistentDataType.STRING);
            return Material.getMaterial(valueString);
        }
        else {
            return Material.AIR;
        }
    }

    public void setTool(ToolType tool, Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (isWorldshaper(item)) {
            NamespacedKey key = new NamespacedKey("worldshaper", "tool-type");
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, tool.toString());
            item.setItemMeta(meta);
        } else {
            player.sendMessage("This is not a worldshaper");
        }

    }
    public ToolType getTool(ItemStack item){
        NamespacedKey key = new NamespacedKey("worldshaper", "tool-type");
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)){
            String valueString = container.get(key, PersistentDataType.STRING);
            return ToolType.valueOf(valueString);
        } else {
          return ToolType.PLACE;
        }
    }
    public void setBrush(BrushType brush, Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (isWorldshaper(item)) {
            NamespacedKey key = new NamespacedKey("worldshaper", "brush-type");
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, brush.toString());
            item.setItemMeta(meta);
        } else {
            player.sendMessage("This is not a worldshaper");
        }

    }
    public BrushType getBrush(ItemStack item){
        NamespacedKey key = new NamespacedKey("worldshaper", "brush-type");
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)){
            String valueString = container.get(key, PersistentDataType.STRING);
            return BrushType.valueOf(valueString);
        } else {
            return BrushType.SINGLEBLOCK;
        }
    }
    public void setAttach(AttachType attach, Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (isWorldshaper(item)) {
            NamespacedKey key = new NamespacedKey("worldshaper", "attach-type");
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, attach.toString());
            item.setItemMeta(meta);
        } else {
            player.sendMessage("This is not a worldshaper");
        }

    }
    public AttachType getAttach(ItemStack item){
        NamespacedKey key = new NamespacedKey("worldshaper", "attach-type");
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)){
            String valueString = container.get(key, PersistentDataType.STRING);
            return AttachType.valueOf(valueString);
        } else {
            return AttachType.ATTACH;
        }
    }
    public void setReplaceOnlyTarget(String value, Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (isWorldshaper(item)) {
            NamespacedKey key = new NamespacedKey("worldshaper", "replace-mode");
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
            item.setItemMeta(meta);
        } else {
            player.sendMessage("This is not a worldshaper");
        }

    }
    public boolean getReplaceOnlyTarget(ItemStack item){
        NamespacedKey key = new NamespacedKey("worldshaper", "replace-mode");
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.STRING)){
            String valueString = container.get(key, PersistentDataType.STRING);
            if(valueString.equals("true")){
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }
    public void setSizeValue(Player player,String keystring, int value){
        ItemStack item = player.getInventory().getItemInMainHand();
        if (isWorldshaper(item)){
            NamespacedKey key = new NamespacedKey("worldshaper", "size-" + keystring);
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, value);
            item.setItemMeta(meta);
        }else {
            player.sendMessage("This is not a worldshaper");
        }
    }
    public int getSizeValue(ItemStack item, String keystring){
        NamespacedKey key = new NamespacedKey("worldshaper", "size-" + keystring);
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (container.has(key, PersistentDataType.INTEGER)){
            return container.get(key, PersistentDataType.INTEGER);
        } else {
            return 1;
        }
    }
    public void itemModifyCommand(Player player,String[] args){
        if(args.length >= 2){
            switch (args[1]){
                case "tool":
                    ToolType tool = ToolType.valueOf(args[2].toUpperCase());
                    setTool(tool, player);
                    break;
                case "brush":
                    BrushType brush = BrushType.valueOf(args[2].toUpperCase());
                    setBrush(brush, player);
                    break;
                case "attach":
                    AttachType attach = AttachType.valueOf(args[2].toUpperCase());
                    setAttach(attach, player);
                    break;
                case "replacemode":
                    if (args[2].equals("true") || args[2].equals("false")){
                        setReplaceOnlyTarget(args[2], player);
                        break;
                    }
                    else {
                        System.out.println("Wrong argument" + args[2]);
                        break;
                    }
                case "size":
                    int value = Integer.parseInt(args[3]);
                    setSizeValue(player, args[2], value);
                    break;
                default:
                    System.out.println("Wrong argument" + args[1]);
            }
        }
        else{
            System.out.println("too short");
        }
    }
}