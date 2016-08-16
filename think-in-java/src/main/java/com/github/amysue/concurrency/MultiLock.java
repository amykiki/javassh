package com.github.amysue.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/8/15.
 */
public class MultiLock {
    public synchronized void f1(int count, String name) {
        if (count-- > 0) {
            System.out.println(name + ":f1() calling f2() with count " + count);
            f2(count, name);
        }
    }

    public synchronized void f2(int count, String name) {
        if (count-- > 0) {
            System.out.println(name + ":f2() calling f1() with count " + count);
            f1(count, name);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final MultiLock multiLock = new MultiLock();
        /*new Thread(){
            public void run() {
                multiLock.f1(10, "T1");
            }
        }.start();*/
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                multiLock.f1(10, "T1");
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                multiLock.f1(20, "T2");
            }
        });
        TimeUnit.MILLISECONDS.sleep(200);
        exec.shutdown();
    }
}
