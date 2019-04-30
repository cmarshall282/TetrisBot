package com.tetrisbot.bot;

public class Connection {
    private Node input;
    private Node output;
    private double weight;

    public Connection(Node input, Node output) {
        this.input = input;
        this.output = output;
    }

    public void passData() {
        output.addInput(weight * input.getOutput());
    }
}
