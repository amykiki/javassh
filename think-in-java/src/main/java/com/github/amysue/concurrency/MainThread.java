package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/7/15.
 */
public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
        System.out.println("\nThe Last Line");
    }
}
