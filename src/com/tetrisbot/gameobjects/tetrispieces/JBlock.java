package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.gameobjects.Block;
import com.tetrisbot.gameobjects.BlockColor;
import com.tetrisbot.utils.TetrisRandom;
import com.tetrisbot.utils.Vector2D;

import java.awt.*;
import java.util.Random;

public class JBlock extends BlockTemplate {
    public JBlock(Random r) {
        super(r);
        rotationState = 3; // will go to state 0 when rotate() is called
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(color, 4, 0);
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

    @Override
    public boolean checkRotation() {
        int tempRot = rotationState;
        tempRot++;
        tempRot %= 4;
        Vector2D block0 = new Vector2D(blocks[0].getX(), blocks[0].getY());
        Vector2D block1 = new Vector2D(blocks[1].getX(), blocks[1].getY());
        Vector2D block2 = new Vector2D(blocks[2].getX(), blocks[2].getY());
        Vector2D block3 = new Vector2D(blocks[3].getX(), blocks[3].getY());

        switch(tempRot) {
            case 0:
                block0 = block0.add(new Vector2D(0, 0));
                block1 = block1.add(new Vector2D(0, 1));
                block2 = block2.add(new Vector2D(1, 1));
                block3 = block3.add(new Vector2D(2, 1));
                break;
            case 1:
                block0 = block0.add(new Vector2D(1, 0));
                block1 = block1.add(new Vector2D(1, 1));
                block2 = block2.add(new Vector2D(1, 2));
                block3 = block3.add(new Vector2D(2, 0));
                break;
            case 2:
                block0 = block0.add(new Vector2D(0, 1));
                block1 = block1.add(new Vector2D(1, 1));
                block2 = block2.add(new Vector2D(2, 1));
                block3 = block3.add(new Vector2D(2, 2));
                break;
            case 3:
                block0 = block0.add(new Vector2D(0, 2));
                block1 = block1.add(new Vector2D(1, 0));
                block2 = block2.add(new Vector2D(1, 1));
                block3 = block3.add(new Vector2D(1, 2));
                break;
        }

        if(block0.x < 0 || block0.x > 9 || block0.y > 19) return false;
        if(block1.x < 0 || block1.x > 9 || block1.y > 19) return false;
        if(block2.x < 0 || block2.x > 9 || block2.y > 19) return false;
        if(block3.x < 0 || block3.x > 9 || block3.y > 19) return false;
        return true;
    }

}
