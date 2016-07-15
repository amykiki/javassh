package com.github.amysue.cha11.controller;

/**
 * Created by Amysue on 2016/6/22.
 */
public abstract class Event {
    private long eventTime;
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }

    @Override
    public String toString() {
//        return "start-" + eventTime + "-delay-" + delayTime + "-current-" + System.nanoTime();
        return "delay-" + delayTime + "-(current-start)-" + (System.nanoTime() - eventTime);
    }

    public abstract void action();
}
