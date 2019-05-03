package com.tetrisbot.gameobjects;

import com.tetrisbot.game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Block {
    private int x;
    private int y;
    private int xPerm;
    private int yPerm;
    private final BlockColor blockColor;

    public Block(BlockColor blockColor, int x, int y) {
        this.x = x;
        this.y = y;
        this.blockColor = blockColor;
    }

    public void render(Game game, Graphics g) {
        game.getBoard().fillCell(g, blockColor, x + xPerm, y + yPerm);
    }

    public void tick() {
        y++;
    }

    public void keyPressed(int key, boolean canMoveRight, boolean canMoveLeft) {
        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            if(canMoveLeft) x--;
        } else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            if(canMoveRight) x++;
        }
    }

    public void setPerm(int xPerm, int yPerm) {
        this.xPerm = xPerm;
        this.yPerm = yPerm;
    }

    public int getxPerm() {
        return xPerm;
    }

    public int getyPerm() {
        return yPerm;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
