package com.tetrisbot.gameobjects;

import com.tetrisbot.game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

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

    public void tick() {
        if(y < 19) y++;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            if(x > 0) x--;
        } else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            if(x < 9) x++;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
