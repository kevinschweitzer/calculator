package com.example.kevinschweitzer.myapplication.util.bus.obervers;

import com.example.kevinschweitzer.myapplication.util.operators.Operator;

public abstract class OperatorObserver extends BusObserver<OperatorObserver.OperatorClass>{

    public OperatorObserver(){ super(OperatorClass.class); }

    public static class OperatorClass{

        private Operator operator;

        public OperatorClass(Operator operator){
            this.operator = operator;
        }

        public Operator getOperator(){
            return operator;
        }
    }

}
