package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.gameobjects.Block;

import java.awt.*;

public class IBlock extends BlockTemplate {

    public IBlock() {
        super();
        rotationState = 0;
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(Color.BLUE, 0, 0);
        }
        blocks[0].setPerm(0, 0);
        blocks[1].setPerm(1, 0);
        blocks[2].setPerm(2, 0);
        blocks[3].setPerm(3, 0);
    }

    @Override
    public void rotate() {
        if(rotationState == 0) rotationState++;
        if(rotationState == 1) rotationState = 0;
        switch(rotationState) {
            case 0:
                blocks[0].setPerm(0, 0);
                blocks[1].setPerm(1, 0);
                blocks[2].setPerm(2, 0);
                blocks[3].setPerm(3, 0);
            case 1:
                blocks[0].setPerm(0, 0);
                blocks[0].setPerm(0, 1);
                blocks[0].setPerm(0, 2);
                blocks[0].setPerm(0, 3);
        }
    }
}
