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
        Instrument a = new Instrument();
        Instrument b = new Wind();
        a.play();
        b.play();
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        String s3 = "hello";
        String s4 = "hello";
        System.out.println(s3 == s4);
        String s5 = "a" + "b" + "c" + "d";
        String s6 = new String("abcd");
        System.out.println(s5 == "abcd");
        System.out.println(s6 == "abcd");
    }
}
