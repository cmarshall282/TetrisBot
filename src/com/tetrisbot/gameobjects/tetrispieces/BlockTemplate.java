package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.game.Game;
import com.tetrisbot.gameobjects.Block;

import java.awt.*;

public abstract class BlockTemplate {

    protected Block[] blocks;
    protected int rotationState;

    protected BlockTemplate() {
        blocks = new Block[4];
        rotationState = 0;
    }

    abstract public void rotate();

    public void tick() {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].tick();
        }
    }

    public void render(Game game, Graphics g) {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].render(game, g);
        }
    }

}
