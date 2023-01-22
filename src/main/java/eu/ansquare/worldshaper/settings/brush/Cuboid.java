package eu.ansquare.worldshaper.settings.brush;

import eu.ansquare.worldshaper.BlockFaceEnumSolver;
import eu.ansquare.worldshaper.MyBlockHolder;
import eu.ansquare.worldshaper.Worldshaper;
import eu.ansquare.worldshaper.itemmanager.ItemManager;
import eu.ansquare.worldshaper.settings.AttachType;
import eu.ansquare.worldshaper.settings.ToolType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.jetbrains.annotations.NotNull;

import javax.tools.Tool;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class Cuboid {
    public void initialCuboid(Player player, ItemStack item, MyBlockHolder target) {
        ItemManager im = new ItemManager();
        if (im.getAttach(item) == AttachType.ATTACH) {
            BlockFace face = target.getBlockFace();
            Block targetBlock = target.getBlock();
            BlockFaceEnumSolver bfes = new BlockFaceEnumSolver();
            Vector<Integer> directionNumbers = bfes.getDirectionNumbers(face);
            int originX = targetBlock.getX() + directionNumbers.elementAt(1);
            int originY = targetBlock.getY() + directionNumbers.elementAt(2);
            int originZ = targetBlock.getZ() + directionNumbers.elementAt(3);
            int sizeX = im.getSizeValue(item, Integer.toString(1)) - 1;
            int sizeY = im.getSizeValue(item, Integer.toString(2)) - 1;
            int sizeZ = im.getSizeValue(item, Integer.toString(3)) - 1;
            int finalX1;
            int finalX2;
            int finalY1;
            int finalY2;
            int finalZ1;
            int finalZ2;
            if ((sizeX % 2 == 0)) {
                finalX1 = originX + sizeX / 2;
                finalX2 = originX - sizeX / 2;
            } else {
                finalX1 = originX + (int) addRemove((double) sizeX, true);
                finalX2 = originX - (int) addRemove((double) sizeX, false);
            }
            if ((sizeZ % 2 == 0)) {
                finalZ1 = originZ + sizeZ / 2;
                finalZ2 = originZ - sizeZ / 2;
            } else {
                finalZ1 = originZ + (int) addRemove((double) sizeZ, true);
                finalZ2 = originZ - (int) addRemove((double) sizeZ, false);
            }
            finalY1 = originY + sizeY;
            finalY2 = originY;
            placeCuboid(finalX1, finalY1, finalZ1, finalX2, finalY2, finalZ2, item, player, targetBlock.getType());
        }
        else if (im.getAttach(item) == AttachType.INSERT){
            BlockFace face = target.getBlockFace();
            Block targetBlock = target.getBlock();
            BlockFaceEnumSolver bfes = new BlockFaceEnumSolver();
            Vector<Integer> directionNumbers = bfes.getDirectionNumbers(face);
            int originX = targetBlock.getX();
            int originY = targetBlock.getY();
            int originZ = targetBlock.getZ();
            int sizeX = im.getSizeValue(item, Integer.toString(1)) - 1;
            int sizeY = im.getSizeValue(item, Integer.toString(2)) - 1;
            int sizeZ = im.getSizeValue(item, Integer.toString(3)) - 1;
            int finalX1 = originX;
            int finalX2 = originX;
            int finalY1 = originY;
            int finalY2 = originY;
            int finalZ1 = originZ;
            int finalZ2 = originZ;
            if(directionNumbers.elementAt(1) != 0){
                finalX1 = originX + sizeX * directionNumbers.elementAt(1) * -1;
                finalX2 = originX;
                if ((sizeY % 2 == 0)) {
                    finalY1 = originY + sizeY / 2;
                    finalY2 = originY - sizeY / 2;
                } else {
                    finalY1 = originY + (int) addRemove((double) sizeY, true);
                    finalY2 = originY - (int) addRemove((double) sizeY, false);
                }
                if ((sizeZ % 2 == 0)) {
                    finalZ1 = originZ + sizeZ / 2;
                    finalZ2 = originZ - sizeZ / 2;
                } else {
                    finalZ1 = originZ + (int) addRemove((double) sizeZ, true);
                    finalZ2 = originZ - (int) addRemove((double) sizeZ, false);
                }
            }
            if(directionNumbers.elementAt(2) != 0){
                finalY1 = originY + sizeY * directionNumbers.elementAt(2) * -1;
                finalY2 = originY;
                if ((sizeX % 2 == 0)) {
                    finalX1 = originX + sizeX / 2;
                    finalX2 = originX - sizeX / 2;
                } else {
                    finalX1 = originX + (int) addRemove((double) sizeX, true);
                    finalX2 = originX - (int) addRemove((double) sizeX, false);
                }
                if ((sizeZ % 2 == 0)) {
                    finalZ1 = originZ + sizeZ / 2;
                    finalZ2 = originZ - sizeZ / 2;
                } else {
                    finalZ1 = originZ + (int) addRemove((double) sizeZ, true);
                    finalZ2 = originZ - (int) addRemove((double) sizeZ, false);
                }
            }
            if(directionNumbers.elementAt(3) != 0){
                finalZ1 = originZ + sizeZ * directionNumbers.elementAt(3) * -1;
                finalZ2 = originZ;
                if ((sizeX % 2 == 0)) {
                    finalX1 = originX + sizeX / 2;
                    finalX2 = originX - sizeX / 2;
                } else {
                    finalX1 = originX + (int) addRemove((double) sizeX, true);
                    finalX2 = originX - (int) addRemove((double) sizeX, false);
                }
                if ((sizeY % 2 == 0)) {
                    finalY1 = originY + sizeY / 2;
                    finalY2 = originY - sizeY / 2;
                } else {
                    finalY1 = originY + (int) addRemove((double) sizeY, true);
                    finalY2 = originY - (int) addRemove((double) sizeY, false);
                }
            }
            placeCuboid(finalX1, finalY1, finalZ1, finalX2, finalY2, finalZ2, item, player, targetBlock.getType());
        }
        else if (im.getAttach(item) == AttachType.CENTER){
            Block targetBlock = target.getBlock();
            int originX = targetBlock.getX();
            int originY = targetBlock.getY();
            int originZ = targetBlock.getZ();
            int sizeX = im.getSizeValue(item, Integer.toString(1)) - 1;
            int sizeY = im.getSizeValue(item, Integer.toString(2)) - 1;
            int sizeZ = im.getSizeValue(item, Integer.toString(3)) - 1;
            int finalX1;
            int finalX2;
            int finalY1;
            int finalY2;
            int finalZ1;
            int finalZ2;
            if ((sizeX % 2 == 0)) {
                finalX1 = originX + sizeX / 2;
                finalX2 = originX - sizeX / 2;
            } else {
                finalX1 = originX + (int) addRemove((double) sizeX, true);
                finalX2 = originX - (int) addRemove((double) sizeX, false);
            }
            if ((sizeY % 2 == 0)) {
                finalY1 = originY + sizeY / 2;
                finalY2 = originY - sizeY / 2;
            } else {
                finalY1 = originY + (int) addRemove((double) sizeY, true);
                finalY2 = originY - (int) addRemove((double) sizeY, false);
            }
            if ((sizeZ % 2 == 0)) {
                finalZ1 = originZ + sizeY / 2;
                finalZ2 = originZ - sizeY / 2;
            } else {
                finalZ1 = originZ + (int) addRemove((double) sizeY, true);
                finalZ2 = originZ - (int) addRemove((double) sizeY, false);
            }



            placeCuboid(finalX1, finalY1, finalZ1, finalX2, finalY2, finalZ2, item, player, targetBlock.getType());
        }

    }
   /* it would probably help if you only did Math.min and max once before yo start doing anything else
    that's not a cost-free function
    it still has to compare it, and you're doing it multiple times per loop for no reason
    you generally might want to get all the blocks, split the work into multiple parts and THEN do one part of that work each tick
    here you basically just delayed everything by 200 ticks
    it still did all the work in a span roughly 2 ticks
if you want to generally keep how it works now, instead of delaying everything by 200 ticks, start with 1 and increment it by 1 each loop
    so loop 1 wil be delayed by 1 tick
    loop 2 by 2 ticks, etc
    that way you're not placing shit ton of blocks on one tick
    you also probably could check if the block is already the type you're setting to*/
    void placeCuboid(int x1, int y1, int z1, int x2, int y2, int z2, ItemStack item, Player player, Material material) {
        ItemManager im = new ItemManager();
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);
        int maxZ = Math.max(z1, z2);
        List<Vector<Integer>> bssa = new ArrayList<Vector<Integer>>();
        for (int x = minX; x <= maxX; x++) {
            int finalX = x;
            for (int y = minY; y >= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block b = player.getWorld().getBlockAt(finalX, y, z);
                    switch (im.getTool(item)) {
                        case REPLACE:
                            if (!b.isEmpty()) {
                                if(im.getReplaceOnlyTarget(item)){
                                    if(b.getType() == material){
                                        b.setType(im.getPickedBlock(item));
                                    }
                                }
                                else {
                                    b.setType(im.getPickedBlock(item));
                                }
                            }
                            break;
                        case PLACE:
                            b.setType(im.getPickedBlock(item));
                            break;
                        case CLEAR:
                            b.setType(Material.AIR);
                            break;
                        case AIRPLACE:
                            if(b.isEmpty()){
                                b.setType(im.getPickedBlock(item));
                            }
                            break;
                        case OVERLAY:
                            Block ba = player.getWorld().getBlockAt(x, y + 1, z);
                            if(ba.isEmpty()){
                                if(!b.isEmpty()){
                                    Vector<Integer> vecs = new Vector<Integer>();
                                    vecs.add(0, x);
                                    vecs.add(1, y);
                                    vecs.add(2, z);
                                    vecs.add(3, y + 1);
                                    bssa.add(vecs);
                                    System.out.println("Flatten");

                                }
                            }
                            break;
                        case FLATTEN:
                            Block bas = player.getWorld().getBlockAt(x, y + 1, z);
                            if(bas.isEmpty()){
                                if(!b.isEmpty()){
                                    Vector<Integer> vecss = new Vector<Integer>();
                                    vecss.add(0, x);
                                    vecss.add(1, y);
                                    vecss.add(2, z);
                                    vecss.add(3, y + 1);
                                    bssa.add(vecss);
                                    System.out.println("Flatten");

                                }
                            }
                            break;
                        default:
                            b.setType(im.getPickedBlock(item));
                    }

                }

            }
            /*new BukkitRunnable() {
                public void run() {

                }
            }.runTaskLater(Worldshaper.getPlugin(), 10);*/
        }
        if (bssa.size() > 0) {
            for (int i = 0; i <= bssa.size(); i++) {
                Vector<Integer> vecs = bssa.get(i);
                if (im.getTool(item) == ToolType.OVERLAY) {
                    player.getWorld().getBlockAt(vecs.get(0), vecs.get(3), vecs.get(2)).setType(im.getPickedBlock(item));
                } else {
                    player.getWorld().getBlockAt(vecs.get(0), vecs.get(1), vecs.get(2)).setType(Material.AIR);
                }
            }
        }

    }
    public double addRemove(double number, boolean add) {
        if (add == true) {
            return number / 2 + 0.5;
        } else {
            return number / 2 - 0.5;

        }
    }
}