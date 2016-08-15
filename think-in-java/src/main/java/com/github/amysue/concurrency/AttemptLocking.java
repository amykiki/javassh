package com.github.amysue.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Amysue on 2016/8/8.
 */
public class AttemptLocking {
    private ReentrantLock rlock = new ReentrantLock();

    public void untimed() {
        boolean captured = rlock.tryLock();
        try {
            System.out.println("Untimed trylock(): " + captured);
        } finally {
            if (captured) {
                rlock.unlock();
            }
        }
    }
    public void timed() {
        boolean captured = false;
        try {
            System.out.println("befor: " + System.currentTimeMillis());
            captured = rlock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("after: " + System.currentTimeMillis());
            System.out.println("timed trylock(2, TimeUnit.Seconds): " + captured);
        } finally {
            if (captured) {
                rlock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
//        al.untimed();
//        al.timed();
        new Thread(){
            {setDaemon(true);}
            public void run() {
                al.rlock.lock();
                System.out.println("Acquired");
            }
        }.start();
        Thread.yield();
        al.untimed();
        al.timed();
    }
}
