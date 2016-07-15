package com.github.amysue.polymorphism;

/**
 * Created by Amysue on 2016/6/22.
 */
public class TestMain {
    public static void main(String[] args) {
        Wind flute = new Wind();
        Instrument.tune(flute);
        Instrument i2 = flute;
        Instrument.tune(i2);
    }
}
