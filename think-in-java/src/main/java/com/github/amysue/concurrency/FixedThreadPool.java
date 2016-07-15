package com.github.amysue.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Amysue on 2016/7/15.
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(4);
        for(int i = 0; i < 6; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
        System.out.println("Waiting For FixedThreadPool To Finish!");
    }
}
