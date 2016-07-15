package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/7/15.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff!");

    }
}
