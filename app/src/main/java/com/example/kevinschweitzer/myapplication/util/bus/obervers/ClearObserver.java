package com.example.kevinschweitzer.myapplication.util.bus.obervers;

public abstract class ClearObserver extends BusObserver<ClearObserver.Clear> {
    public ClearObserver(){ super(Clear.class); }

    public static class Clear{

    }
}
