package com.tetrisbot.game;

import com.tetrisbot.utils.TetrisRandom;

import java.awt.*;
import java.util.Random;

public class Piece {
    private Block[] blocks = new Block[4];
    private BlockConfig config;

    public Piece(Random r) {
        config = TetrisRandom.chooseConfig(r);

        switch(config) {
            case I_BLOCK:
                System.out.println("I");
                break;
            case J_BLOCK:
                System.out.println("J");
                break;
            case L_BLOCK:
                System.out.println("L");
                break;
            case O_BLOCK:
                System.out.println("O");
                break;
            case S_BLOCK:
                System.out.println("S");
                break;
            case T_BLOCK:
                System.out.println("T");
                break;
            case Z_BLOCK:
                System.out.println("Z");
                break;
        }
    }

    public void render(Game game, Graphics g) {
        for(Block block : blocks) block.render(game, g);
    }
}
