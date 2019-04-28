package com.tetrisbot.utils;

import java.awt.*;

public class Vector2D extends Point {

    public Vector2D(int x, int y) {
        super(x, y);
    }

    public Vector2D add(Vector2D input) {
        return new Vector2D(x + input.x, y + input.y);
    }

}
