package com.tetrisbot.utils;

import com.tetrisbot.game.BlockConfig;

import java.util.Random;

public class TetrisRandom {
    public static BlockConfig chooseConfig(Random r) {
        int index = r.nextInt(BlockConfig.values().length);
        return BlockConfig.values()[index];
    }
}
