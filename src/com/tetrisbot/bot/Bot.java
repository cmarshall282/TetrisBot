package com.tetrisbot.bot;

import com.tetrisbot.game.Game;
import com.tetrisbot.utils.TetrisRandom;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Bot {
    private Node[] inputs = new Node[200];
    private Node[] outputs = new Node[5];

    private ArrayList<Node> hiddenNodes = new ArrayList<>();
    private ArrayList<Connection> hiddenConnections = new ArrayList<>();

    private int fitness;

    private Game game;

    public Bot(Game game) {
        for(int i = 0; i < inputs.length; i++) {
            inputs[i] = new Node(0);
        }

        for(int i = 0; i < outputs.length; i++) {
            outputs[i] = new Node(1);
        }

        fitness = 0;
        this.game = game;
    }

    private void makeDecision() {
        for(Connection connection: hiddenConnections) {
            if(connection.isEnabled())connection.passData();
        }

        if(outputs[0].getOutput() == 1) {
            game.keyPressed(KeyEvent.VK_RIGHT);
        } else if(outputs[1].getOutput() == 1) {
            game.keyPressed(KeyEvent.VK_LEFT);
        } else if(outputs[2].getOutput() == 1) {
            game.keyPressed(KeyEvent.VK_UP);
        } else if(outputs[3].getOutput() == 1) {
            game.keyPressed(KeyEvent.VK_DOWN);
        } else if(outputs[4].getOutput() == 1) {
            game.keyPressed(KeyEvent.VK_SHIFT);
        }
    }

    public void calculateFitness() {
        //change to game is playing
        while(true) {
            makeDecision();
        }
    }

    public void normalizeFitness(int totalFitness) {
        fitness /= totalFitness;
        fitness *= 100;
    }

    public void mutate(double mutationRate, Random r) {
        if(r.nextDouble() < mutationRate) {
            int choice = r.nextInt(3);
            if(choice == 0) {
                //create a connection
                hiddenConnections.add(new Connection(TetrisRandom.chooseNode(hiddenNodes, r), TetrisRandom.chooseNode(hiddenNodes, r), 5));
            } else if(choice == 1) {
                //create a node
                hiddenNodes.add(new Node(5));
            } else {
                //change a weight
                TetrisRandom.chooseConnection(hiddenConnections, r).mutate(r);
            }
        }
    }

    public int getFitness() {
        return fitness;
    }

}
