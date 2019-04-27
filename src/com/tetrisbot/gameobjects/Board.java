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
    private BufferedImage[] blockImages;

    public Board(Game game, int width, int height, int cellSize, int offset) {
        this.game = game;
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.offset = offset;

        blockImages = new BufferedImage[6];
        blockImages[0] = TetrisGraphics.loadImage("pieces/GreenPiece.png");
        blockImages[1] = TetrisGraphics.loadImage("pieces/BluePiece.png");
        blockImages[2] = TetrisGraphics.loadImage("pieces/OrangePiece.png");
        blockImages[3] = TetrisGraphics.loadImage("pieces/PurplePiece.png");
        blockImages[4] = TetrisGraphics.loadImage("pieces/RedPiece.png");
        blockImages[5] = TetrisGraphics.loadImage("pieces/YellowPiece.png");
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(offset, 0, 400, 800);
    }

    public void fillCell(Graphics g, BlockColor c, int x, int y) {
        switch(c) {
            case GREEN:
                g.drawImage(blockImages[0], x * cellSize + offset, y * cellSize, game);
                break;
            case BLUE:
                g.drawImage(blockImages[1], x * cellSize + offset, y * cellSize, game);
                break;
            case ORANGE:
                g.drawImage(blockImages[2], x * cellSize + offset, y * cellSize, game);
                break;
            case PURPLE:
                g.drawImage(blockImages[3], x * cellSize + offset, y * cellSize, game);
                break;
            case RED:
                g.drawImage(blockImages[4], x * cellSize + offset, y * cellSize, game);
                break;
            case YELLOW:
                g.drawImage(blockImages[5], x * cellSize + offset, y * cellSize, game);
                break;
        }
    }
}
