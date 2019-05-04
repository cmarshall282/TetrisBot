package com.tetrisbot.game;

import com.tetrisbot.gameobjects.Block;
import com.tetrisbot.gameobjects.BlockConfig;
import com.tetrisbot.gameobjects.Board;
import com.tetrisbot.gameobjects.MainMenu;
import com.tetrisbot.gameobjects.tetrispieces.BlockTemplate;
import com.tetrisbot.input.KeyInput;
import com.tetrisbot.input.MouseInput;
import com.tetrisbot.utils.TetrisGraphics;
import com.tetrisbot.utils.TetrisRandom;
import com.tetrisbot.utils.audio.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Game extends Canvas implements Runnable {

    private static final int width = 600;
    private static final int height = 800;
    private static final String title = "Tetris";
    private boolean running;
    private Thread thread;
    private State gameState;
    private MainMenu mainMenu;
    private Board board;
    private double gameDelay;
    private Random r;
    private BlockTemplate currentBlock;
    private BlockTemplate holdBlock;
    private BlockTemplate nextBlock;
    private Window window;
    private int score;
    private int rows;
    private int level;
    private Sound song;
    private Sound moveSound;
    private Sound clearRowSound;
    private double updateDelay;
    private boolean canSwap;
    private ArrayList<BlockConfig> blockSet;

    public Game() {
        setSize(new Dimension(width, height));
        r = new Random();
        blockSet = TetrisRandom.chooseBlocks(r);
        song = new Sound("TetrisTheme.wav", this);
        moveSound = new Sound("MovementSound.wav", this);
        clearRowSound = new Sound("ClearRowSound.wav", this);
        song.setVolume(0.1);
        nextBlock = TetrisRandom.initNext(blockSet);
        currentBlock = TetrisRandom.initBlock(blockSet);
        nextBlock.moveToNext();
        holdBlock = null;
        gameDelay = 0.0;
        running = false;
        window = new Window(title, this);
        gameState = State.MainMenu;
        mainMenu = new MainMenu(this);
        addMouseListener(new MouseInput(this));
        addKeyListener(new KeyInput(this));
        board = new Board(this,10, 20, 40, 0);
        score = 0;
        rows = 0;
        level = 1;
        updateDelay = 60.0;
        canSwap = true;
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
            if (gameDelay >= 10.0) {
                if(currentBlock.isMovingDown()) {
                    if(!currentBlock.checkCollision(board)) {
                        currentBlock.tick();
                        this.addScore(1);
                    }
                }
            }
            if (gameDelay >= updateDelay) {
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
                    if(board.clearFullRows()) clearRowSound.play();
                    if(blockSet.size() == 1) blockSet.addAll(TetrisRandom.chooseBlocks(r));
                    nextBlock = TetrisRandom.initNext(blockSet);
                    nextBlock.moveToNext();
                    currentBlock = TetrisRandom.initBlock(blockSet);
                    canSwap = true;
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
            nextBlock.render(this, g);
            if(holdBlock != null) holdBlock.render(this, g);
            g.setColor(Color.BLACK);
            g.fillRect(398, 0, 4, height);
            g.setFont(new Font("Verdana", Font.BOLD, 24));
            g.setColor(Color.WHITE);
            g.drawString("Level", 420, 280);
            g.drawString("Rows", 420, 320);
            g.drawString("Score", 420, 360);
            g.drawString("Hold", 470, 30);
            g.drawString("Next", 470, 610);
            g.setFont(new Font("Verdana", Font.PLAIN, 24));
            g.drawString("" + level, 510, 280);
            g.drawString("" + rows, 510, 320);
            g.drawString(getStringScore(), 510, 360);
        }

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        if(thread == null) thread = new Thread(this);
        thread.start();
        running = true;
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

    public void keyPressed(int key) {
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_UP ||
        key == KeyEvent.VK_A || key == KeyEvent.VK_D || key == KeyEvent.VK_W) {
            moveSound.play();
        }
        if(key == KeyEvent.VK_SHIFT) {
            if(canSwap) {
                if(holdBlock == null) {
                    holdBlock = currentBlock;
                    Point p = currentBlock.getPosition();
                    currentBlock = TetrisRandom.initBlock(blockSet);
                    currentBlock.setPosition(p.x, p.y);
                    holdBlock.moveToHold();
                } else {
                    BlockTemplate tempBlock = currentBlock;
                    currentBlock = holdBlock;
                    Point p = tempBlock.getPosition();
                    currentBlock.setPosition(p.x, p.y);
                    holdBlock = tempBlock;
                    holdBlock.moveToHold();
                    canSwap = false;
                }
            }
        }
        currentBlock.keyPressed(key, board);
    }

    public void keyReleased(int key) {
        currentBlock.keyReleased(key);
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void addRows(int nRows) {
        this.rows += nRows;
    }

    public void updateLevel() {
        if((rows / level) >= (5 * level)) {
            if (updateDelay > 30.0) {
                updateDelay -= 5.0 * level;
            }
            this.level++;
        }
    }

    public int getLevel() {
        return level;
    }

    public String getStringScore() {
        return "" + score;
    }

    public void playSong() {
        song.loop();
    }

    public void playMoveSound() {
        moveSound.play();
    }

    public void playClearRowSound() {
        clearRowSound.play();
    }
}
