package com.github.amysue.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Amysue on 2016/7/15.
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
        System.out.println("Waiting For SingleThreadExecutor To Finish");
    }
}
