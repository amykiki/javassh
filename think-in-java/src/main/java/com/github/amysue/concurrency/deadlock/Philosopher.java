package com.github.amysue.concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/8/25.
 */
public class Philosopher implements Runnable{
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor ) {
        this.id = id;
        this.ponderFactor = ponderFactor;
        this.right = right;
        this.left = left;
    }

    private void pause() throws InterruptedException{
        if(ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor*250));
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                System.out.println(this + " grabbing right");
                right.take();
                System.out.println(this + " grabbing left");
                left.take();
                System.out.println(this + " eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " exiting via interrupt");
        }
    }
}
