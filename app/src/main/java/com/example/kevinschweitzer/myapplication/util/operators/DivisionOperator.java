package com.example.kevinschweitzer.myapplication.util.operators;

public class DivisionOperator extends Operator {

    public DivisionOperator(){ symbol = "/"; }

    @Override
    public Integer operate(int x, int y) {
        if(y==0){
            return null;
        }

        return x/y;
    }
}
