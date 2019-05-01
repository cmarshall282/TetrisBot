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
        "RedPiece.png", "YellowPiece.png", "LightBluePIece.png"};

        blockImages = new BufferedImage[7];
        for(int i = 0; i < blockImages.length; i++) {
            blockImages[i] = TetrisGraphics.loadImage("pieces/" + fileNames[i]);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
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

        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString("Score", 10, 25);
        g.drawString(game.getStringScore(), 10, 50);
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
            case LIGHT_BLUE:
                g.drawImage(blockImages[6], x * cellSize + offset, y * cellSize, game);
        }
    }

    public void setOccupiedColors(int x, int y, BlockColor c) {
        if(x >= 0 && x < width && y > 0 && y < height)
            this.occupiedColors[x][y] = c;
    }

    public boolean cellOccupied(int x, int y) {
        if(x >= 0 && x < width && y > 0 && y < height) {
            if (occupiedColors[x][y] != null) {
                return true;
            }
        }
        return false;
    }

    public void clearFullRows() {
        // Initialize a list of rows to clear, set each value to -1 to mean null
        int[] rowsToClear = new int[4];
        for(int i = 0; i < rowsToClear.length; i++) {
            rowsToClear[i] = -1;
        }

        // loop  through each row and add it to rowsToClear if the row is full
        int listIndex = 0;
        for(int row = 0; row < height; row++) {
            int blocksInRow = 0;
            for(int column = 0; column < width; column++) {
                if(cellOccupied(column, row)) blocksInRow++;
            }
            if(blocksInRow == width) {
                rowsToClear[listIndex] = row;
                listIndex++;
            }
        }

        // loop through each full row and clear it, then move all above rows down
        for(int row : rowsToClear) {
            if(row != -1) {
                for(int column = 0; column < width; column++) {
                    setOccupiedColors(column, row, null);
                }
                for(int lowerRow = row; lowerRow > 0; lowerRow--) {
                    for(int column = 0; column < width; column++) {
                        occupiedColors[column][lowerRow] = occupiedColors[column][lowerRow - 1];
                    }
                }
            }
        }

        // add to score
        switch (listIndex) {
            case 1:
                game.addScore(40);
                break;
            case 2:
                game.addScore(100);
                break;
            case 3:
                game.addScore(300);
                break;
            case 4:
                game.addScore(1200);
                break;
        }
    }
}
