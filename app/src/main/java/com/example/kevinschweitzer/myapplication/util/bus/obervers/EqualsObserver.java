package com.example.kevinschweitzer.myapplication.util.bus.obervers;

public abstract class EqualsObserver extends BusObserver<EqualsObserver.Equals> {
    public EqualsObserver(){ super(Equals.class); }

    public static class Equals{

    }
}
