package eu.ansquare.worldshaper.gui;

import eu.ansquare.worldshaper.itemmanager.ItemManager;
import eu.ansquare.worldshaper.settings.AttachType;
import eu.ansquare.worldshaper.settings.BrushType;
import eu.ansquare.worldshaper.settings.ToolType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class GuiManager implements Listener {
    private Inventory inv;

    ItemStack cuboid;
    ItemStack sphere;
    ItemStack cylinder;
    ItemStack surface;
    ItemStack cluster;
    ItemStack singleblock;

    ItemStack place;
    ItemStack replace;
    ItemStack clear;
    ItemStack airplace;
    ItemStack overlay;
    ItemStack flatten;

    ItemStack attach;
    ItemStack center;
    ItemStack insert;

    ItemStack replacemode;

    ItemStack size1;
    ItemStack size2;
    ItemStack size3;

    ItemStack selectedblock;
/*    you should probably just implement your own inventoryholder instead of using
    inventory field, your current setup breaks if a second player opens your gui (assuming .equals even works for inventories)*/
    public void ExampleGui(ItemStack item, Player player) {
        // Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
        inv = Bukkit.createInventory(null, 54, Component.text("Worldshaper"));

        // Put the items into the inventory
        initializeItems(item);
        player.openInventory(inv);
    }
    public void initializeItems(ItemStack item) {
        fill(0, 8, Material.BLACK_STAINED_GLASS_PANE);
        createBrushMenu(item);
        createToolMenu(item);
        createAttachMenu(item);
        createReplaceModeMenu(item);
        createSizeMenu(item);
        inv.setItem(48, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        fill(50, 3, Material.BLACK_STAINED_GLASS_PANE);

    }

    void createBrushMenu(ItemStack item){
        cuboid = new ItemStack(Material.RED_WOOL, 1);
        cylinder = new ItemStack(Material.RED_WOOL, 1);
        sphere = new ItemStack(Material.RED_WOOL, 1);
        surface = new ItemStack(Material.RED_WOOL, 1);
        cluster = new ItemStack(Material.RED_WOOL, 1);
        singleblock = new ItemStack(Material.RED_WOOL, 1);
        ItemManager im = new ItemManager();
        BrushType curentbrush = im.getBrush(item);
        switch (curentbrush){
            case CUBOID:
                cuboid.setType(Material.LIME_WOOL);
                break;
            case CYLINDER:
                cylinder.setType(Material.LIME_WOOL);
                break;
            case SPHERE:
                sphere.setType(Material.LIME_WOOL);
                break;
            case SURFACE:
                surface.setType(Material.LIME_WOOL);
                break;
            case CLUSTER:
                cluster.setType(Material.LIME_WOOL);
                break;
            case SINGLEBLOCK:
                singleblock.setType(Material.LIME_WOOL);
                break;
        }
        cuboid.setItemMeta(metaManager(cuboid.getItemMeta(), BrushType.CUBOID.getLore(), "Cuboid"));
        sphere.setItemMeta(metaManager(sphere.getItemMeta(), BrushType.SPHERE.getLore(), "Sphere"));
        cylinder.setItemMeta(metaManager(cylinder.getItemMeta(), BrushType.CYLINDER.getLore(), "Cylinder"));
        surface.setItemMeta(metaManager(surface.getItemMeta(), BrushType.SURFACE.getLore(), "Surface"));
        cluster.setItemMeta(metaManager(cluster.getItemMeta(), BrushType.CLUSTER.getLore(), "Cluster"));
        singleblock.setItemMeta(metaManager(singleblock.getItemMeta(), BrushType.SINGLEBLOCK.getLore(), "Single block"));

        inv.setItem(9, cuboid);
        inv.setItem(10, sphere);
        inv.setItem(11, cylinder);
        inv.setItem(12, surface);
        inv.setItem(13, cluster);
        inv.setItem(14, singleblock);
    }
    void createToolMenu(ItemStack item){
        fill(27, 5, Material.AIR);
        place = new ItemStack(Material.RED_WOOL, 1);
        replace = new ItemStack(Material.RED_WOOL, 1);
        clear = new ItemStack(Material.RED_WOOL, 1);
        airplace = new ItemStack(Material.RED_WOOL, 1);
        overlay = new ItemStack(Material.RED_WOOL, 1);
        flatten = new ItemStack(Material.RED_WOOL, 1);
        ItemManager im = new ItemManager();
        BrushType curentbrush = im.getBrush(item);
        ToolType currentTool = im.getTool(item);
        switch (currentTool){
            case PLACE:
                place.setType(Material.LIME_WOOL);
                break;
            case REPLACE:
                replace.setType(Material.LIME_WOOL);
                break;
            case CLEAR:
                clear.setType(Material.LIME_WOOL);
                break;
            case AIRPLACE:
                airplace.setType(Material.LIME_WOOL);
                break;
            case OVERLAY:
                overlay.setType(Material.LIME_WOOL);
                break;
            case FLATTEN:
                flatten.setType(Material.LIME_WOOL);
                break;
        }
        place.setItemMeta(metaManager(place.getItemMeta(), ToolType.PLACE.getLore(), "Place"));
        replace.setItemMeta(metaManager(replace.getItemMeta(), ToolType.REPLACE.getLore(), "Replace"));
        clear.setItemMeta(metaManager(clear.getItemMeta(), ToolType.CLEAR.getLore(), "Clear"));
        airplace.setItemMeta(metaManager(airplace.getItemMeta(), ToolType.AIRPLACE.getLore(), "Air replace"));
        overlay.setItemMeta(metaManager(overlay.getItemMeta(), ToolType.OVERLAY.getLore(), "Overlay"));
        flatten.setItemMeta(metaManager(flatten.getItemMeta(), ToolType.FLATTEN.getLore(), "Flatten"));
        if(curentbrush.isSupportsPlace()){
            inv.setItem(27, place);
        }
        if(curentbrush.isSupportsReplace()){
            inv.setItem(28, replace);
        }
        if(curentbrush.isSupportsClear()){
            inv.setItem(29, clear);
        }
        if(curentbrush.isSupportsAirPlace()){
            inv.setItem(30, airplace);
        }
        if(curentbrush.isSupportsOverlay()){
            inv.setItem(31, overlay);
        }
        if(curentbrush.isSupportsFlatten()){
            inv.setItem(32, flatten);
        }
    }

    void createAttachMenu(ItemStack item) {
        ItemManager im = new ItemManager();
        BrushType curentbrush = im.getBrush(item);
        AttachType currentattch = im.getAttach(item);
        if (curentbrush == BrushType.CUBOID || curentbrush == BrushType.SPHERE || curentbrush == BrushType.CYLINDER) {
            attach = new ItemStack(Material.RED_WOOL, 1);
            center = new ItemStack(Material.RED_WOOL, 1);
            insert = new ItemStack(Material.RED_WOOL, 1);
            switch (currentattch) {
                case ATTACH:
                    attach.setType(Material.LIME_WOOL);
                    break;
                case CENTER:
                    center.setType(Material.LIME_WOOL);
                    break;
                case INSERT:
                    insert.setType(Material.LIME_WOOL);
                    break;
            }
            attach.setItemMeta(metaManager(attach.getItemMeta(), AttachType.ATTACH.getLore(), "Attach"));
            center.setItemMeta(metaManager(center.getItemMeta(), AttachType.CENTER.getLore(), "Center"));
            insert.setItemMeta(metaManager(insert.getItemMeta(), AttachType.INSERT.getLore(), "Insert"));

            inv.setItem(45, attach);
            inv.setItem(46, center);
            inv.setItem(47, insert);

        }
        else {
            fill(45, 2, Material.BLACK_STAINED_GLASS_PANE);
        }
    }
    void createReplaceModeMenu(ItemStack item){
        replacemode = new ItemStack(Material.RED_WOOL, 1);
        ItemManager im = new ItemManager();
        if(im.getReplaceOnlyTarget(item)){
            replacemode.setType(Material.LIME_WOOL);
        }
        replacemode.setItemMeta(metaManager(replacemode.getItemMeta(), "Toggles the replace tool replacing only blocks of the same type as the targeted block", "Toggle replace mode"));
        inv.setItem(49, replacemode);
    }
    void createSizeMenu(ItemStack item){
        //17 26 35
        ItemManager im = new ItemManager();
        BrushType currentBrush = im.getBrush(item);
        size1 = new ItemStack(Material.RED_WOOL, im.getSizeValue(item, "1"));
        size2 = new ItemStack(Material.LIME_WOOL, im.getSizeValue(item, "2"));
        size3 = new ItemStack(Material.BLUE_WOOL, im.getSizeValue(item, "3"));
        if(currentBrush == BrushType.CUBOID){
            size1.setItemMeta(metaManager(size1.getItemMeta(), "The size of the cuboid along the X axis", "Width"));
            size2.setItemMeta(metaManager(size2.getItemMeta(), "The size of the cuboid along the Y axis", "Height"));
            size3.setItemMeta(metaManager(size3.getItemMeta(), "The size of the cuboid along the Z axis", "Length"));
            inv.setItem(17, size1);
            inv.setItem(26, size2);
            inv.setItem(35, size3);
        }
        if(currentBrush == BrushType.CYLINDER || currentBrush == BrushType.SPHERE){
            size1.setItemMeta(metaManager(size1.getItemMeta(), "The radius of the" + currentBrush.toString().toLowerCase(), "Radius"));
            size2.setItemMeta(metaManager(size2.getItemMeta(), "The height of the" + currentBrush.toString().toLowerCase(), "Height"));
            inv.setItem(17, size1);
            inv.setItem(26, size2);
        }
        if(currentBrush == BrushType.SURFACE || currentBrush == BrushType.CLUSTER){
            size1.setItemMeta(metaManager(size1.getItemMeta(), "The radius of the selected" + currentBrush.toString().toLowerCase(), "Radius"));
            inv.setItem(17, size1);
        }
        if(currentBrush == BrushType.SINGLEBLOCK) {
            inv.clear(17);
            inv.clear(26);
            inv.clear(35);
        }
    }

    ItemMeta metaManager(ItemMeta meta, String lore, String name){
        TextComponent cname = Component.text(name);
        meta.displayName(cname);
        TextComponent clore = Component.text(lore);
        List<Component> lorelist = new ArrayList<Component>();
        lorelist.add(clore);
        meta.lore(lorelist);
        return meta;
    }
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inv)) {
            int slot = e.getSlot();
            Player player = (Player) e.getWhoClicked();
            if(slot >=9 && slot <=14){
                switchBrush(slot, player);
            }
            else if(slot>=27 && slot<=32) {
                if(e.getInventory().getItem(slot) != null){
                    switchTool(slot, player);
                }
                else e.getWhoClicked().sendMessage("Unsupported tool type");
            }
            else if(slot>=45 && slot<=47) {
                if(e.getInventory().getItem(slot).getType() != Material.BLACK_STAINED_GLASS_PANE){
                    switchAttach(slot, player);
                }
                else e.getWhoClicked().sendMessage("Unsupported tool type");
            } else if (slot == 49) {
                toggleReplaceMode(player);
            }
            else if (slot == 17 || slot == 26 || slot == 35) {
                if (e.getInventory().getItem(slot) != null){
                    if (e.isLeftClick()) {
                        adjustSizes(slot, player, false, e.isShiftClick());
                    } else {
                        adjustSizes(slot, player, true, e.isShiftClick());
                    }
                }
            }


            e.setCancelled(true);
        }
    }
    void switchBrush(int slot, Player player){
        ItemManager im = new ItemManager();
        switch (slot){
            case 9:
                im.setBrush(BrushType.CUBOID, player);
                break;
            case 10:
                im.setBrush(BrushType.SPHERE, player);
                break;
            case 11:
                im.setBrush(BrushType.CYLINDER, player);
                break;
            case 12:
                im.setBrush(BrushType.SURFACE, player);
                break;
            case 13:
                im.setBrush(BrushType.CLUSTER, player);
                break;
            case 14:
                im.setBrush(BrushType.SINGLEBLOCK, player);
                break;
        }
        createBrushMenu(player.getInventory().getItemInMainHand());
        createToolMenu(player.getInventory().getItemInMainHand());
        createAttachMenu(player.getInventory().getItemInMainHand());
        createSizeMenu(player.getInventory().getItemInMainHand());
    }
    void switchTool(int slot, Player player){
        ItemManager im = new ItemManager();
        switch (slot){
            case 27:
                im.setTool(ToolType.PLACE, player);
                break;
            case 28:
                im.setTool(ToolType.REPLACE, player);
                break;
            case 29:
                im.setTool(ToolType.CLEAR, player);
                break;
            case 30:
                im.setTool(ToolType.AIRPLACE, player);
                break;
            case 31:
                im.setTool(ToolType.OVERLAY, player);
                break;
            case 32:
                im.setTool(ToolType.FLATTEN, player);
                break;
        }
        createToolMenu(player.getInventory().getItemInMainHand());
    }
    void switchAttach(int slot, Player player){
        ItemManager im = new ItemManager();
        switch (slot){
            case 45:
                im.setAttach(AttachType.ATTACH, player);
                break;
            case 46:
                im.setAttach(AttachType.CENTER, player);
                break;
            case 47:
                im.setAttach(AttachType.INSERT, player);
                break;
        }
        createAttachMenu(player.getInventory().getItemInMainHand());
    }
    void toggleReplaceMode(Player player){
        ItemManager im = new ItemManager();
        if(im.getReplaceOnlyTarget(player.getInventory().getItemInMainHand())){
            im.setReplaceOnlyTarget("false", player);
        }
        else {
            im.setReplaceOnlyTarget("true", player);
        }
        createReplaceModeMenu(player.getInventory().getItemInMainHand());
    }
    void adjustSizes(int slot, Player player, boolean click, boolean shiftclick){
        ItemManager im = new ItemManager();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(click){
            switch (slot){
                case 17:
                    if (shiftclick){
                        im.setSizeValue(player, "1", im.getSizeValue(item, "1") + 5);
                    }
                    else {
                        im.setSizeValue(player, "1", im.getSizeValue(item, "1") + 1);
                    }
                    break;
                case 26:
                    if (shiftclick){
                        im.setSizeValue(player, "2", im.getSizeValue(item, "2") + 5);
                    }
                    else {
                        im.setSizeValue(player, "2", im.getSizeValue(item, "2") + 1);
                    }
                    break;
                case 35:
                    if (shiftclick){
                        im.setSizeValue(player, "3", im.getSizeValue(item, "3") + 5);
                    }
                    else {
                        im.setSizeValue(player, "3", im.getSizeValue(item, "3") + 1);
                    }
                    break;
            }
        }
        else {
            switch (slot){
                case 17:
                    if (shiftclick){
                        im.setSizeValue(player, "1", im.getSizeValue(item, "1") - 5);
                    }
                    else {
                        im.setSizeValue(player, "1", im.getSizeValue(item, "1") - 1);
                    }
                    break;
                case 26:
                    if (shiftclick){
                        im.setSizeValue(player, "2", im.getSizeValue(item, "2") - 5);
                    }
                    else {
                        im.setSizeValue(player, "2", im.getSizeValue(item, "2") - 1);
                    }
                    break;
                case 35:
                    if (shiftclick){
                        im.setSizeValue(player, "3", im.getSizeValue(item, "3") - 5);
                    }
                    else {
                        im.setSizeValue(player, "3", im.getSizeValue(item, "3") - 1);
                    }
                    break;
            }
        }
        if(im.getSizeValue(item, "1") <= 0){
            im.setSizeValue(player, "1", 1);
        }
        if(im.getSizeValue(item, "2") <= 0){
            im.setSizeValue(player, "2", 1);
        }
        if(im.getSizeValue(item, "3") <= 0){
            im.setSizeValue(player, "3", 1);
        }
        createSizeMenu(item);
    }

    void fill(int start, int amount, Material material){
        for(int i = start; i <= start + amount; i++){
            inv.setItem(i, new ItemStack(material));
        }
    }
}
