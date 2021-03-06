package com.tetrisbot.gameobjects.tetrispieces;

import com.tetrisbot.game.Game;
import com.tetrisbot.gameobjects.Block;
import com.tetrisbot.gameobjects.BlockColor;
import com.tetrisbot.gameobjects.Board;
import com.tetrisbot.utils.TetrisRandom;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public abstract class BlockTemplate {

    protected Block[] blocks;
    protected int rotationState;
    protected BlockColor color;
    private boolean isMovingDown;

    protected BlockTemplate() {
        isMovingDown = false;
        blocks = new Block[4];
        rotationState = 0;
    }

    abstract public void rotate();
    abstract public boolean checkRotation(Board board);

    public void tick() {
        for(Block block : blocks) {
            block.tick();
        }
    }

    public void render(Game game, Graphics g) {
        for(int i = 0; i < blocks.length; i++) {
            blocks[i].render(game, g);
        }
    }

    public void keyPressed(int key, Board board) {
        boolean canMove = true;
        if(key == KeyEvent.VK_LEFT) {
            if(checkCollision(board, -1)) canMove = false;
        } else if(key == KeyEvent.VK_RIGHT) {
            if(checkCollision(board, 1)) canMove = false;
        }
        if(canMove) {
            boolean canMoveLeft = true;
            boolean canMoveRight = true;
            boolean canRotate = true;

            for (int i = 0; i < blocks.length; i++) {
                if (blocks[i].getX() + blocks[i].getxPerm() == 9) canMoveRight = false;
                if (blocks[i].getX() + blocks[i].getxPerm() == 0) canMoveLeft = false;
                if (blocks[i].getY() + blocks[i].getyPerm() == 19) canRotate = false;
                if (canRotate) canRotate = checkRotation(board);
            }

            for (int i = 0; i < blocks.length; i++) {
                blocks[i].keyPressed(key, canMoveRight, canMoveLeft);
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && canRotate) rotate();
            if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) isMovingDown = true;
        }
    }

    public void keyReleased(int key) {
        if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) isMovingDown = false;
    }

    public boolean checkCollision(Board board) {
        for(Block block : blocks) {
            final int x = block.getX() + block.getxPerm();
            int y = block.getY() + block.getyPerm(); // not final because it is modified later, x isn't
            if(y == 19) return true;
            y += 1; // get y position of next tick
            if(board.cellOccupied(x, y)) return true;
        }
        return false;
    }

    public boolean checkCollision(Board board, int x) {
        // Check collision overload for key input only
        for(Block block : blocks) {
            final int nextX = block.getX() + block.getxPerm() + x;
            final int nextY = block.getY() + block.getyPerm();
            if(board.cellOccupied(nextX, nextY)) return true;
        }
        return false;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public BlockColor getColor() {
        return color;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public void moveToHold() {
        // Set it to its initial position for display
        rotationState = 3;
        rotate();
        for(Block block : blocks) {
            block.setPosition(11, 2);
        }
    }

    public void moveToNext() {
        for(Block block : blocks) {
            block.setPosition(11, 16);
        }
    }

    public void setPosition(int x, int y) {
        for(Block block : blocks) {
            block.setPosition(x, y);
        }
    }

    public Point getPosition() {
        return new Point(blocks[0].getX(), blocks[0].getY());
    }
}
