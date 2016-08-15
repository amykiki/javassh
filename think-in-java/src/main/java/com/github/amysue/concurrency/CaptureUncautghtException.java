package com.github.amysue.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Amysue on 2016/8/8.
 */
public class CaptureUncautghtException {
    public void unCaptureException() {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
            exec.shutdown();
        } catch (RuntimeException e) {
            System.out.println("Exception has been handled!" + e.getMessage());
        }

    }

    public void captureException() {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread());
        exec.shutdown();
    }
    public static void main(String[] args) {
        CaptureUncautghtException ce = new CaptureUncautghtException();
//        ce.unCaptureException();
        ce.captureException();
    }
}
