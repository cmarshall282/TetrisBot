package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.gameobjects.Block;
import com.tetrisbot.gameobjects.BlockColor;
import com.tetrisbot.utils.TetrisRandom;

import java.util.Random;

public class JBlock extends BlockTemplate {
    public JBlock(Random r) {
        super();
        rotationState = 3; // will go to state 0 when rotate() is called
        BlockColor c = TetrisRandom.chooseColor(r);
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(c, 5, 0);
        }
        rotate();
    }

    @Override
    public void rotate() {
        rotationState++;
        rotationState %= 4;

        switch (rotationState) {
            case 0:
                blocks[0].setPerm(0, 0);
                blocks[1].setPerm(0, 1);
                blocks[2].setPerm(1, 1);
                blocks[3].setPerm(2, 1);
                break;
            case 1:
                blocks[0].setPerm(1, 0);
                blocks[1].setPerm(1, 1);
                blocks[2].setPerm(1, 2);
                blocks[3].setPerm(2, 0);
                break;
            case 2:
                blocks[0].setPerm(0, 1);
                blocks[1].setPerm(1, 1);
                blocks[2].setPerm(2, 1);
                blocks[3].setPerm(2, 2);
                break;
            case 3:
                blocks[0].setPerm(0, 2);
                blocks[1].setPerm(1, 0);
                blocks[2].setPerm(1, 1);
                blocks[3].setPerm(1, 2);
                break;
        }

    }

}
