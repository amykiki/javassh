package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/7/15.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting For More LiftOffs!");
    }
}
