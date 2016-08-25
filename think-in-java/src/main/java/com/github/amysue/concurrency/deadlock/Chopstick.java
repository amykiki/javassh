package com.github.amysue.concurrency.deadlock;

/**
 * Created by Amysue on 2016/8/25.
 */
public class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException{
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() throws InterruptedException{
        taken = false;
        notifyAll();
    }
}
