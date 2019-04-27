package com.tetrisbot.gameobjects;

import com.tetrisbot.game.Game;
import com.tetrisbot.utils.TetrisGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Board {
    private final int width;
    private final int height;
    private final int cellSize;
    private final int offset;
    private final Game game;

    public Board(Game game, int width, int height, int cellSize, int offset) {
        this.game = game;
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.offset = offset;
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(offset, 0, 400, 800);
    }

    public void fillCell(Graphics g, BlockColor c, int x, int y) {
        BufferedImage image;
        switch(c) {
            case GREEN:
                image = TetrisGraphics.loadImage("pieces/GreenPiece.png");
                break;
            case BLUE:
                image = TetrisGraphics.loadImage("pieces/BluePiece.png");
                break;
            case ORANGE:
                image = TetrisGraphics.loadImage("pieces/OrangePiece.png");
                break;
            case PURPLE:
                image = TetrisGraphics.loadImage("pieces/PurplePiece.png");
                break;
            case RED:
                image = TetrisGraphics.loadImage("pieces/RedPiece.png");
                break;
            case YELLOW:
                image = TetrisGraphics.loadImage("pieces/YellowPiece.png");
                break;
            default:
                image = null;
                break;
        }
        g.drawImage(image, x * cellSize + offset, y * cellSize, game);
    }
}
