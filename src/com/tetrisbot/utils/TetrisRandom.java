package com.tetrisbot.utils;

import com.tetrisbot.gameobjects.BlockConfig;
import com.tetrisbot.gameobjects.tetrispieces.*;

import java.util.Random;

public class TetrisRandom {
    public static BlockConfig chooseConfig(Random r) {
        int index = r.nextInt(BlockConfig.values().length);
        return BlockConfig.values()[index];
    }

    public static BlockTemplate initBlock(Random r) {
        BlockConfig blockConfig = chooseConfig(r);
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
}
