package com.github.amysue.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/8/16.
 */
class Num {
    private int i = 0;

    public void add() {
        i++;
    }

    public void subtract() {
        i--;
    }

    @Override
    public String toString() {
        return "num = " + i;
    }

}

class Add implements Runnable {
    private Num num;

    public Add(Num num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (num) {
                num.add();
                System.out.println(Thread.currentThread() + " " + num);
                Thread.yield();
            }
        }
    }
}

class Subtract implements Runnable {
    private Num num;

    public Subtract(Num num) {
        this.num = num;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (num) {
                num.subtract();
                System.out.println(Thread.currentThread() + " " + num);
                Thread.yield();
            }
        }
    }
}

public class AddSubTract {
    public static void main(String[] args) throws InterruptedException{
        Num             num  = new Num();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Add(num));
        exec.execute(new Add(num));
        exec.execute(new Subtract(num));
        exec.execute(new Subtract(num));
        TimeUnit.MILLISECONDS.sleep(10);
        exec.shutdownNow();
    }
}
