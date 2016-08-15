package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/8/8.
 */
public class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Sleeper: " + getName() + " Begin");
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interupted. " + " isInterrupted(): " + isInterrupted());
            return;
        }
        System.out.println(getName() + " has awakedned");
    }
}
