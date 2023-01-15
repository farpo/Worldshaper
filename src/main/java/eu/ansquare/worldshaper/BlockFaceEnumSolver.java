package eu.ansquare.worldshaper;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;

public class BlockFaceEnumSolver {
    public Vector<Integer> getDirectionNumbers(BlockFace face){
        Vector<Integer> vctri = new Vector<Integer>();
        vctri.add(0, 0);

        switch (face.name()){
            case "UP":
                vctri.add(1, 0);
                vctri.add(2, 1);
                vctri.add(3, 0);
                break;
            case "DOWN":
                vctri.add(1, 0);
                vctri.add(2, -1);
                vctri.add(3, 0);
                break;
            case "WEST":
                vctri.add(1, -1);
                vctri.add(2, 0);
                vctri.add(3, 0);
                break;
            case "EAST":
                vctri.add(1, 1);
                vctri.add(2, 0);
                vctri.add(3, 0);
                break;
            case "SOUTH":
                vctri.add(1, 0);
                vctri.add(2, 0);
                vctri.add(3, 1);
                break;
            case "NORTH":
                vctri.add(1, 0);
                vctri.add(2, 0);
                vctri.add(3, -1);
                break;
            default:
                vctri.add(1, 0);
                vctri.add(2, 0);
                vctri.add(3, 0);
        }
        return vctri;
    }
}
