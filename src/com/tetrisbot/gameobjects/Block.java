package com.tetrisbot.gameobjects;

import com.tetrisbot.game.Game;

import java.awt.*;

public class Block {
    private int x;
    private int y;
    private final Color blockColor;

    public Block(Color blockColor) {
        this.blockColor = blockColor;
    }

    public void render(Game game, Graphics g) {
        game.getBoard().fillCell(g, blockColor, x, y);
    }

    public void updateX() {

    }

    public void updateY() {
        if(y < 19) y++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
