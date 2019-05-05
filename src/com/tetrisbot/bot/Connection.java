package com.tetrisbot.bot;

import java.util.Random;

public class Connection {
    private Node input;
    private Node output;

    private double weight;
    private int innovation;
    private boolean enabled;
    private boolean passedData;

    public Connection(Node input, Node output, int innovation) {
        this.input = input;
        this.output = output;
        this.innovation = innovation;
        enabled = true;
        passedData = false;
    }

    public void passData() {
        output.addInput(weight * input.getOutput());
        passedData = true;
    }

    public void mutate(Random r) {
        weight = r.nextDouble();
    }

    public void reset() {
        passedData = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean passedData() {
        return passedData;
    }
}
