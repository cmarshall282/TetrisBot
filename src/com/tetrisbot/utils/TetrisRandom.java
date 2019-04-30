package com.tetrisbot.utils;

import com.tetrisbot.gameobjects.BlockColor;
import com.tetrisbot.gameobjects.BlockConfig;
import com.tetrisbot.gameobjects.tetrispieces.*;

import java.util.Random;

public class TetrisRandom {
    public static BlockConfig chooseConfig(Random r) {
        int index = r.nextInt(BlockConfig.values().length);
        return BlockConfig.values()[index];
    }

    public static BlockColor chooseColor(Random r) {
        int index = r.nextInt(BlockColor.values().length);
        return BlockColor.values()[index];
    }

    public static BlockTemplate initBlock(Random r) {
        BlockConfig blockConfig = chooseConfig(r);
        BlockTemplate output = null;
        switch(blockConfig) {
            case I_BLOCK:
                output =  new IBlock(r);
                break;
            case J_BLOCK:
                output = new JBlock(r);
                break;
            case L_BLOCK:
                output = new LBlock(r);
                break;
            case O_BLOCK:
                output = new OBlock(r);
                break;
            case S_BLOCK:
                output = new SBlock(r);
                break;
            case T_BLOCK:
                output = new TBlock(r);
                break;
            case Z_BLOCK:
                output = new ZBlock(r);
                break;
        }
        return output;
    }
}
