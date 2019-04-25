package com.tetrisbot.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TetrisGraphics {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(TetrisGraphics.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}