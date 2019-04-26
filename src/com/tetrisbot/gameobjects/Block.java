package com.tetrisbot.gameobjects;

import com.tetrisbot.game.Game;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    private int width;
    private int height;

    public Block() {

    }

    public void render(Game game, Graphics g) {
        g.drawRect(x, y, width, height);
    }

    public void updateX() {

    }

    public void updateY() {
        y--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
