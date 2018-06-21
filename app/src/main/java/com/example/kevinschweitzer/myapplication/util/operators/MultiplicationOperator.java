package com.example.kevinschweitzer.myapplication.util.operators;

public class MultiplicationOperator extends Operator {

    public MultiplicationOperator(){ symbol = "*"; }

    @Override
    public Integer operate(int x, int y) {
        return x*y;
    }
}
