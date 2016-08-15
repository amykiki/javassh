package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/8/8.
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Caught " + t.getName() + " " + e);
    }
}
