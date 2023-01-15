package eu.ansquare.worldshaper;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class MyBlockHolder {
    private BlockFace blockFace;
    private Block block;

    public MyBlockHolder(BlockFace blockFace, Block block) {
        this.blockFace = blockFace;
        this.block = block;
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public void setBlockFace(BlockFace blockFace) {
        this.blockFace = blockFace;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
