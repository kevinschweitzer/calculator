package com.example.kevinschweitzer.myapplication.util.operators;

public class PlusOperator extends Operator{

    public PlusOperator(){
        symbol = "+";
    }

    @Override
    public Integer operate(int x, int y) {
        return x+y;
    }
}
