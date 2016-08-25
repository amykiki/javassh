package com.github.amysue.concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/8/25.
 */
public class FixedDiningPhilosophers {
    public static void main(String[] args) throws Exception{
        int ponder = 5;
        int size = 5;
        boolean timeout = false;
        Chopstick[] sticks = new Chopstick[size];
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for(int i = 0; i < size; i++) {
            if (i < size - 1) {
                exec.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
            } else {
                exec.execute(new Philosopher(sticks[0], sticks[i],i, ponder));
            }
        }
        if (timeout) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Predd 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
