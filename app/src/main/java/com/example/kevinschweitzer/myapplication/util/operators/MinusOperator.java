package com.example.kevinschweitzer.myapplication.util.operators;


public class MinusOperator extends Operator {

    public MinusOperator(){ symbol = "-"; }

    @Override
    public Integer operate(int x, int y) {
        return x-y;
    }
}
