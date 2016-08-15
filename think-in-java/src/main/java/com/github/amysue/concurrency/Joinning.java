package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/8/8.
 */
public class Joinning {
    public static void main(String[] args) {
        Sleeper sleeper1 = new Sleeper("Amy", 1500);
        Sleeper sleeper2 = new Sleeper("Kevin", 2000);
        Joiner joiner1 = new Joiner("AmyJava", sleeper1);
        Joiner joiner2 = new Joiner("KevinJava", sleeper2);
        sleeper2.interrupt();

    }
}
