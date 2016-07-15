package com.github.amysue.generics;

/**
 * Created by Amysue on 2016/7/5.
 */
public class Fruit {
    private static long count;
    private final long id = count++;
    @Override
    public String toString() {
        return "This is " + this.getClass().getSimpleName() + ", id=" + id;
    }

    public long getId() {
        return id;
    }
}
