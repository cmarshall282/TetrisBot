package com.tetrisbot.gameobjects;

import com.tetrisbot.game.Game;
import com.tetrisbot.game.State;
import com.tetrisbot.utils.TetrisGraphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenu {

    Game game;

    public MainMenu(Game game) {
        this.game = game;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        BufferedImage backGround = TetrisGraphics.loadImage("MainMenuBackground.png");
        BufferedImage logo = TetrisGraphics.loadImage("TetrisLogo.png");
        BufferedImage playerButton = TetrisGraphics.loadImage("playerButton.png");
        BufferedImage loadButton = TetrisGraphics.loadImage("loadButton.png");
        BufferedImage trainButton = TetrisGraphics.loadImage("trainButton.png");
        g.drawImage(backGround, 0, 0, game);
        g.drawImage(playerButton, 150, 450, game);
        g.drawImage(loadButton, 150, 530, game);
        g.drawImage(trainButton, 150, 610, game);
        g.drawImage(logo, 100, 80, game);
    }

    public void mousePressed(MouseEvent e) {
        final Point loc = e.getPoint();
        final Rectangle playerButton = new Rectangle(150, 450, 300, 60);
        final Rectangle loadButton = new Rectangle(150, 530, 300, 60);
        final Rectangle trainButton = new Rectangle(150, 610, 300, 60);
        if(playerButton.contains(loc)) {
            System.out.println("playerButton was pressed");
            game.setState(State.Running);
        } else if (loadButton.contains(loc)) {
            System.out.println("loadButton was pressed");
        } else if (trainButton.contains(loc)) {
            System.out.println("trainButton was pressed");
        }
    }

}
