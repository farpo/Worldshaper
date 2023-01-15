package eu.ansquare.worldshaper;

import eu.ansquare.worldshaper.itemmanager.ItemManager;
import eu.ansquare.worldshaper.settings.*;
import eu.ansquare.worldshaper.settings.brush.Cuboid;
import eu.ansquare.worldshaper.settings.brush.Cylinder;
import eu.ansquare.worldshaper.settings.brush.Sphere;
import eu.ansquare.worldshaper.settings.brush.Surface;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.function.Predicate;

public class OperationManager {

    public void findTargetBlock(@NotNull Player player, int range, boolean pick, ItemStack item){
        ItemManager im = new ItemManager();
        Location playerLoc = player.getLocation();
        World playerWorld = player.getWorld();
        @Nullable Predicate<Entity> filter = new Predicate<Entity>() {
            @Override
            public boolean test(Entity entity) {
                return false;
            }
        };
        RayTraceResult trace = playerWorld.rayTrace(player.getEyeLocation(), playerLoc.getDirection()
                , range, FluidCollisionMode.ALWAYS, false, 0, filter);

        if(trace ==null){
            System.out.println("Error! Hit block is null");
        } else {
            Block hitBlock = trace.getHitBlock();
            if(pick == true){
                im.setPickedBlock(hitBlock, item);
            } else {
                @Nullable BlockFace blockFace = trace.getHitBlockFace();
                MyBlockHolder targetBlockHolder = new MyBlockHolder(blockFace, hitBlock);
                if (im.getBrush(item) == BrushType.SINGLEBLOCK) {
                    switch (im.getTool(item)) {
                        case REPLACE:
                            tempUseReplace(targetBlockHolder, item);
                            break;
                        case PLACE:
                            tempUsePlace(targetBlockHolder, item, player);
                            break;
                        case CLEAR:
                            tempUseClear(targetBlockHolder, item);
                            break;
                        default:
                            tempUsePlace(targetBlockHolder, item, player);
                    }
                }
                else {
                    switch (im.getBrush(item)){
                        case CUBOID:
                            Cuboid cuboid = new Cuboid();
                            cuboid.initialCuboid(player, item, targetBlockHolder);
                            break;
                        case SPHERE:
                            Sphere sphere = new Sphere();
                            sphere.place(player, item);
                            break;
                        case SURFACE:
                            Surface surface = new Surface();
                            surface.place(player, item);
                            break;
                        case CYLINDER:
                            Cylinder cylinder = new Cylinder();
                            cylinder.place(player, item);
                            break;
                        default:
                            player.sendMessage("nobrush");
                    }
                }
            }
        }
    }
    public void tempUsePlace(MyBlockHolder target, ItemStack item, Player player){
        ItemManager im = new ItemManager();
        int x = target.getBlock().getX();
        int y = target.getBlock().getY();
        int z = target.getBlock().getZ();
        switch (target.getBlockFace().name()){
            case "UP":
                Block blockUp = player.getWorld().getBlockAt(x, y + 1, z);
                blockUp.setType(im.getPickedBlock(item));
                break;
            case "DOWN":
                Block blockDown = player.getWorld().getBlockAt(x, y - 1, z);
                blockDown.setType(im.getPickedBlock(item));
                break;
            case "WEST":
                Block blockWest = player.getWorld().getBlockAt(x - 1, y, z);
                blockWest.setType(im.getPickedBlock(item));
                break;
            case "EAST":
                Block blockEast = player.getWorld().getBlockAt(x + 1, y, z);
                blockEast.setType(im.getPickedBlock(item));
                break;
            case "SOUTH":
                Block blockSouth = player.getWorld().getBlockAt(x, y, z + 1);
                blockSouth.setType(im.getPickedBlock(item));
                break;
            case "NORTH":
                Block blockNorth = player.getWorld().getBlockAt(x, y, z - 1);
                blockNorth.setType(im.getPickedBlock(item));
                break;
        }
    }
    public void tempUseReplace(MyBlockHolder target, ItemStack item){
        ItemManager im = new ItemManager();
        target.getBlock().setType(im.getPickedBlock(item));
    }
    public void tempUseClear(MyBlockHolder target, ItemStack item){
        target.getBlock().setType(Material.AIR);
    }
}