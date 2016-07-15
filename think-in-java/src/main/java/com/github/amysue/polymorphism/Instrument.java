package com.github.amysue.polymorphism;

/**
 * Created by Amysue on 2016/6/22.
 */
public class Instrument {
    public void play() {
        System.out.println("Base Play");
    }

    public static void tune(Instrument instrument) {
        instrument.play();
    }
}
