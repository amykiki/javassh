package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/8/16.
 */
public class WaitAndNotify {
    private int n = 0;
    private boolean childrun = true;
    public synchronized void child() {
        try {
            while (!childrun) {
                wait();
            }
            System.out.println("child run");
            for(int i = 0; i < 10; i++) {
                n++;
            }
            childrun = false;
            System.out.println("n = " + n);
            notify();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void parent() {
        try {
            while (childrun) {
                wait();
            }
            System.out.println("Parent run");
            for(int i = 0; i < 100; i++) {
                n--;
            }
            childrun = true;
            System.out.println("n = " + n);
            notify();
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        WaitAndNotify wan = new WaitAndNotify();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 50; i++) {
                    wan.child();
                }
            }
        }).start();
        for(int i = 0; i < 50; i++) {
            wan.parent();
        }
    }
}
