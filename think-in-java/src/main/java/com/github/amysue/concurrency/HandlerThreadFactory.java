package com.github.amysue.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Amysue on 2016/8/8.
 */
public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("Created Thread " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println(getClass().getName() + " eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}
