package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/8/8.
 */
public class Joiner extends Thread{
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Joiner: " + getName() + " Begin");
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println(getName() + " is Interrupted");
        }
        System.out.println(getName() + " join Completed");

    }
}
