package com.tetrisbot.gameobjects.tetrispieces;

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

    }

    public void render(Graphics g) {

    }

}
