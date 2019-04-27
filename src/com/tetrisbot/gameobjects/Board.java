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
    private final BufferedImage bluePiece;
    private final Game game;

    public Board(Game game, int width, int height, int cellSize, int offset) {
        this.game = game;
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.offset = offset;
        bluePiece = TetrisGraphics.loadImage("pieces/BluePiece.png");
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(offset, 0, 400, 800);
        g.setColor(Color.WHITE);
        /*for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                g.drawRect(i * cellSize + offset, j * cellSize, 40, 40);
            }
        }*/
    }

    public void fillCell(Graphics g, Color c, int x, int y) {
        if (c == Color.BLUE) {
            g.drawImage(bluePiece,x * cellSize + offset, y * cellSize, game);
        }
    }
}
