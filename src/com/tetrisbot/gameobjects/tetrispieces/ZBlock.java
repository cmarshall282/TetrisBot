package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.gameobjects.Block;
import com.tetrisbot.utils.TetrisRandom;

import java.util.Random;

public class ZBlock extends BlockTemplate {

    public ZBlock(Random r) {
        super();
        rotationState = 0;
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(TetrisRandom.chooseColor(r), 0, 0);
        }
        blocks[0].setPerm(0, 0);
        blocks[1].setPerm(1, 0);
        blocks[2].setPerm(1, 1);
        blocks[3].setPerm(2, 1);
    }

    @Override
    public void rotate() {
        rotationState++;
        rotationState %= 2;

        switch(rotationState) {
            case 0:
                blocks[0].setPerm(0, 0);
                blocks[1].setPerm(1, 0);
                blocks[2].setPerm(1, 1);
                blocks[3].setPerm(2, 1);
                break;
            case 1:
                blocks[0].setPerm(1, 0);
                blocks[1].setPerm(1, 1);
                blocks[2].setPerm(0, 1);
                blocks[3].setPerm(0, 2);
                break;
        }
    }

    @Override
    public boolean checkRotation() {
        return true;
    }
}
