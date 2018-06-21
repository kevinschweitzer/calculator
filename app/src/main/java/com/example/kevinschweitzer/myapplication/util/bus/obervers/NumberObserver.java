package com.example.kevinschweitzer.myapplication.util.bus.obervers;

public abstract class NumberObserver extends BusObserver<NumberObserver.Number> {
    public NumberObserver(){ super(Number.class); }

    public static class Number{

        private String number;

        public Number(String number){
            this.number = number;
        }
        public String getString(){
            return number;
        }
    }
}
