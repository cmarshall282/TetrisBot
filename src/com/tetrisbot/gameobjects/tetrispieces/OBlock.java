package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.gameobjects.Block;
import com.tetrisbot.gameobjects.BlockColor;
import com.tetrisbot.gameobjects.Board;
import com.tetrisbot.utils.TetrisRandom;

import java.util.Random;

public class OBlock extends BlockTemplate {
    public OBlock(Random r) {
        super(r);
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(color, 4, 0);
        }

        blocks[0].setPerm(1, 0);
        blocks[1].setPerm(2, 0);
        blocks[2].setPerm(2, 1);
        blocks[3].setPerm(1, 1);
    }

    @Override
    public void rotate() {

    }

    @Override
    public boolean checkRotation(Board board) {
        return true;
    }
}
