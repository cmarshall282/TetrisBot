package com.tetrisbot.gameobjects;

import java.awt.*;

public class Board {
    private final int width;
    private final int height;
    private final int cellSize;
    private final int offset;

    public Board(int width, int height, int cellSize, int offset) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.offset = offset;
    }

    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(offset, 0, 400, 800);
    }

    public void fillCell(Graphics g, Color c, int x, int y) {
        g.setColor(c);

        g.fillRect((x * cellSize) + offset, y * cellSize, cellSize, cellSize);
    }
}
