package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.game.Game;
import com.tetrisbot.gameobjects.Block;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class BlockTemplate {

    protected Block[] blocks;
    protected int rotationState;

    protected BlockTemplate() {
        blocks = new Block[4];
        rotationState = 0;
    }

    abstract public void rotate();

    public void tick() {

        boolean flag = true;

        for(int i = 0; i < blocks.length; i++) {
            if(blocks[i].getY() + blocks[i].getyPerm() == 19) flag = false;
        }

        if(flag) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].tick();
            }
        }
    }

    public void render(Game game, Graphics g) {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].render(game, g);
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].keyPressed(e);
        }
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) rotate();
    }

}
