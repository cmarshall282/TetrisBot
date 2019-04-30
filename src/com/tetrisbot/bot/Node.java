package com.tetrisbot.bot;

public class Node {
    double output;
    double totalInput;

    public Node() {
        output = 0;
        totalInput = 0;
    }

    public void addInput(double input) {
        totalInput += input;
    }

    public double getOutput() {
        return output;
    }

    public void reset() {
        output = 0;
        totalInput = 0;
    }
}
