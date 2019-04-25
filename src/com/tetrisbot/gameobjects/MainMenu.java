package com.tetrisbot.gameobjects;

import com.tetrisbot.game.Game;
import com.tetrisbot.utils.ImageLoader;

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
        BufferedImage backGround = ImageLoader.loadImage("MainMenuBackground.png");
        BufferedImage logo = ImageLoader.loadImage("TetrisLogo.png");
        BufferedImage playerButton = ImageLoader.loadImage("playerButton.png");
        BufferedImage loadButton = ImageLoader.loadImage("loadButton.png");
        BufferedImage trainButton = ImageLoader.loadImage("trainButton.png");
        g.drawImage(backGround, 0, 0, game);
        g.drawImage(playerButton, 150, 450, game);
        g.drawImage(loadButton, 150, 530, game);
        g.drawImage(trainButton, 150, 610, game);
        g.drawImage(logo, 100, 80, game);
    }

    public void mousePressed(MouseEvent e) {

    }

}
