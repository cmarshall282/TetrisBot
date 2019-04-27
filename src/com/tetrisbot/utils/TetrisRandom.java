package com.tetrisbot.utils;

import com.tetrisbot.gameobjects.BlockColor;
import com.tetrisbot.gameobjects.BlockConfig;

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
}
