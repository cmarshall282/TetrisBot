package com.tetrisbot.game;

import com.tetrisbot.gameobjects.Board;
import com.tetrisbot.gameobjects.MainMenu;
import com.tetrisbot.input.MouseInput;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    private static final int width = 600;
    private static final int height = 800;
    private static final String title = "TETRIS BOT HA | dedicated to shawnipedia";
    private boolean running;
    private Thread thread;
    private State gameState;
    private MainMenu mainMenu;
    private Board board;

    public Game() {
        running = false;
        new Window(width, height, title, this);
        gameState = State.MainMenu;
        mainMenu = new MainMenu(this);
        addMouseListener(new MouseInput(this));
        board = new Board(10, 20, 40, 100);
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
                tick();
                delta--;
            }
            if(running) {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {

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
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
        }

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
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
}
