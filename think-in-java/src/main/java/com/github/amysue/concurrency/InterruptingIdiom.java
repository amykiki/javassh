package com.github.amysue.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/8/15.
 */
class NeedsCleanup{
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("Cleaning up "  + id);
    }
}
class Blocked3 implements Runnable{
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        System.out.println("Calculating");
                        for(int i = 1; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E)/d;
                        }
                        System.out.println("Finished thime-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                }finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test");
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        }
    }
}
public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(new Blocked3());
        t.start();
//        int sleep = 500; //exit at n1
        int sleep = 1005; // exit at n2
//        int sleep = 1500; // exit at n2 ~ n1
        TimeUnit.MILLISECONDS.sleep(sleep);
        t.interrupt();
    }
}
