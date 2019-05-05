package com.tetrisbot.bot;

import java.util.ArrayList;

public class Node {
    private double output;
    private double totalInput;
    private int innovation;

    private ArrayList<Connection> inputConnections;

    public Node(int innovation) {
        output = 0;
        totalInput = 0;
        this.innovation = innovation;
    }

    public void addInput(double input) {
        totalInput += input;
    }

    public double getOutput() {
        calculateOutput();
        return output;
    }

    public void reset() {
        output = 0;
        totalInput = 0;
    }

    private void calculateOutput() {
        checkInputs();
        if(totalInput > 1.0) output = 1;
        else output = 0;
    }

    private void checkInputs() {
        for(Connection input: inputConnections) {
            if(!input.passedData()) {
                input.passData();
            }
        }
    }
}
