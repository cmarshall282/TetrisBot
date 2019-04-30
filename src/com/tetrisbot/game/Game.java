package com.tetrisbot.game;

import com.tetrisbot.gameobjects.Block;
import com.tetrisbot.gameobjects.Board;
import com.tetrisbot.gameobjects.MainMenu;
import com.tetrisbot.gameobjects.tetrispieces.BlockTemplate;
import com.tetrisbot.input.KeyInput;
import com.tetrisbot.input.MouseInput;
import com.tetrisbot.utils.TetrisRandom;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    private static final int width = 600;
    private static int height = 800;
    private static final String title = "Tetris";
    private boolean running;
    private Thread thread;
    private State gameState;
    private MainMenu mainMenu;
    private Board board;
    private double gameDelay;
    private Random r;
    private BlockTemplate currentBlock;
    private Window window;

    public Game() {
        if(System.getProperty("os.name").contains("Windows")) {
            //height += 29;
            height += 29;
        } else if(System.getProperty("os.name").contains("Mac")) {
            System.out.println("Mac");
            height += 22;
        }

        r = new Random();
        currentBlock = TetrisRandom.initBlock(r);
        gameDelay = 0.0;
        running = false;
        window = new Window(width, height, title, this);
        gameState = State.MainMenu;
        mainMenu = new MainMenu(this);
        addMouseListener(new MouseInput(this));
        addKeyListener(new KeyInput(this));
        board = new Board(this,10, 20, 40, 100);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick(delta);
                delta--;
            }
            if(running) {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                window.setTitle("Tetris | " + frames + " FPS");
                frames = 0;
            }
        }
        stop();
    }

    public void tick(double delta) {
        if(gameState == State.Running) {
            gameDelay += delta;
            if (gameDelay >= 60.0) {
                gameDelay = 0.0;
                if(!currentBlock.checkCollision(board)) {
                    currentBlock.tick();
                } else {
                    Block[] blocks = currentBlock.getBlocks();
                    for(Block block : blocks) {
                        final int x = block.getX() + block.getxPerm();
                        final int y = block.getY() + block.getyPerm();
                        board.setOccupiedColors(x, y, currentBlock.getColor());
                    }
                    currentBlock = TetrisRandom.initBlock(r);
                }
            }
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if(gameState == State.MainMenu) {
            mainMenu.render(g);
        } else if(gameState == State.Running){
            board.render(g);
            currentBlock.render(this, g);
        }

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        running = true;
        if(thread == null) thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    public void processMouseClick(MouseEvent e) {
        mainMenu.mousePressed(e);
    }

    public Board getBoard() {
        return board;
    }

    public void setState(State state) {
        this.gameState = state;
    }

    public void keyPressed(KeyEvent e) {
        currentBlock.keyPressed(e);
    }

}
