package com.tetrisbot.utils;

import com.tetrisbot.bot.Connection;
import com.tetrisbot.bot.Node;
import com.tetrisbot.gameobjects.BlockConfig;
import com.tetrisbot.gameobjects.tetrispieces.*;

import java.lang.reflect.Array;
import java.util.*;

public class TetrisRandom {

    public static ArrayList<BlockConfig> chooseBlocks(Random r) {
        List<BlockConfig> blockConfigs = Arrays.asList(BlockConfig.values());
        ArrayList<BlockConfig> output = new ArrayList<>(blockConfigs);
        Collections.shuffle(output);
        return output;
    }

    public static BlockTemplate initBlock(List<BlockConfig> blockSet) {
        BlockConfig blockConfig = blockSet.get(0);
        blockSet.remove(0);
        return getBlock(blockConfig);
    }

    public static BlockTemplate initNext(List<BlockConfig> blockSet) {
        //NOTE: always initialize next block BEFORE the current one
        BlockConfig blockConfig = blockSet.get(1); //block after current one
        return getBlock(blockConfig);
    }

    private static BlockTemplate getBlock(BlockConfig blockConfig) {
        //Utility function for initializers, not meant for public access
        BlockTemplate output = null;
        switch(blockConfig) {
            case I_BLOCK:
                output =  new IBlock();
                break;
            case J_BLOCK:
                output = new JBlock();
                break;
            case L_BLOCK:
                output = new LBlock();
                break;
            case O_BLOCK:
                output = new OBlock();
                break;
            case S_BLOCK:
                output = new SBlock();
                break;
            case T_BLOCK:
                output = new TBlock();
                break;
            case Z_BLOCK:
                output = new ZBlock();
                break;
        }
        return output;
    }

    public static Connection chooseConnection(ArrayList<Connection> connections, Random r) {
        int index = r.nextInt(connections.size());
        return connections.get(index);
    }

    public static Node chooseNode(ArrayList<Node> nodes, Random r) {
        int index = r.nextInt(nodes.size());
        return nodes.get(index);
    }
}
