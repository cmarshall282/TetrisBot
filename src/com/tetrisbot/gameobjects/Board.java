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
    private BlockColor[][] occupiedColors;

    public Board(Game game, int width, int height, int cellSize, int offset) {
        this.game = game;
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.offset = offset;
        this.occupiedColors = new BlockColor[width][height];

        // set every value to null to start
        for(int x = 0; x < occupiedColors.length; x++) {
            for(int y = 0; y < occupiedColors[x].length; y++) {
                occupiedColors[x][y] = null;
            }
        }

        String[] fileNames = {"GreenPiece.png", "BluePiece.png", "OrangePiece.png", "PurplePiece.png",
        "RedPiece.png", "YellowPiece.png"};

        blockImages = new BufferedImage[6];
        for(int i = 0; i < blockImages.length; i++) {
            blockImages[i] = TetrisGraphics.loadImage("pieces/" + fileNames[i]);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(offset, 0, 400, 800);
        for(int x = 0; x < occupiedColors.length; x++) {
            for(int y = 0; y < occupiedColors[x].length; y++) {
                BlockColor color = occupiedColors[x][y];
                if(color != null) {
                    fillCell(g, color, x, y);
                }
            }
        }
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

    public void setOccupiedColors(int x, int y, BlockColor c) {
        this.occupiedColors[x][y] = c;
    }

    public boolean cellOccupied(int x, int y) {
        if(occupiedColors[x][y] != null) {
            return true;
        }
        return false;
    }
}
