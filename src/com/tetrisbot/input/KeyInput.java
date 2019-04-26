package com.tetrisbot.input;

import com.tetrisbot.game.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Game game;

    public KeyInput(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
        System.out.println("[DEBUG]: You pressed the '" + e.getKeyChar() + "' key.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("[DEBUG]: You released the '" + e.getKeyChar() + "' key.");
    }

}
