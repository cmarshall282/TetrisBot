package com.tetrisbot.game;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
        frame.setSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(Window.class.getClassLoader().getResource("TetrisLogo.png")).getImage());
        frame.setResizable(false);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}
