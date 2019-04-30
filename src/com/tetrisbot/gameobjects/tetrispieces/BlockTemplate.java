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

    protected BlockTemplate(Random r) {
        blocks = new Block[4];
        color = TetrisRandom.chooseColor(r);
        rotationState = 0;
    }

    abstract public void rotate();
    abstract public boolean checkRotation();

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

    public void keyPressed(KeyEvent e) {
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

    public Block[] getBlocks() {
        return blocks;
    }

    public BlockColor getColor() {
        return color;
    }
}
