package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.game.Game;
import com.tetrisbot.gameobjects.Block;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class BlockTemplate {

    protected Block[] blocks;
    protected int rotationState;
    private boolean frozen;

    protected BlockTemplate() {
        frozen = false;
        blocks = new Block[4];
        rotationState = 0;
    }

    abstract public void rotate();
    abstract public boolean checkRotation();

    public void tick() {

        boolean flag = true;

        for(int i = 0; i < blocks.length; i++) {
            if(blocks[i].getY() + blocks[i].getyPerm() == 19) flag = false;
        }

        if(flag) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].tick();
            }
        } else if(!frozen) {
            freeze();
        }
    }

    public void render(Game game, Graphics g) {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].render(game, g);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(!frozen) {
            int key = e.getKeyCode();

            boolean canMoveLeft = true;
            boolean canMoveRight = true;
            boolean canRotate = true;

            for (int i = 0; i < blocks.length; i++) {
                if (blocks[i].getX() + blocks[i].getxPerm() == 9) canMoveRight = false;
                if (blocks[i].getX() + blocks[i].getxPerm() == 0) canMoveLeft = false;
                if (blocks[i].getY() + blocks[i].getyPerm() == 19) canRotate = false;
                if (canRotate) canRotate = checkRotation();
            }

            for (int i = 0; i < blocks.length; i++) {
                blocks[i].keyPressed(e, canMoveRight, canMoveLeft);
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && canRotate) rotate();
        }
    }

    public void freeze() {
        frozen = true;
    }

    public boolean isFrozen() {
        return frozen;
    }

}
