package com.github.amysue.concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/8/25.
 */
public class DeaklockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 0;
        int size = 5;
        boolean timeout = false;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for(int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for(int i = 0; i < size; i++) {
            exec.execute(new Philosopher(sticks[i], sticks[(i+1)%size], i, ponder));
        }
        if (timeout) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
