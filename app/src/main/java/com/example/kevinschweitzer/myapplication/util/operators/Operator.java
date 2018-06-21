package com.example.kevinschweitzer.myapplication.util.operators;

public abstract class Operator {

    protected String symbol;


    public abstract Integer operate(int x, int y);

    public String getSymbol(){
        return symbol;
    }
}
