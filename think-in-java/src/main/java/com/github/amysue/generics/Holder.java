package com.github.amysue.generics;

/**
 * Created by Amysue on 2016/7/5.
 */
public class Holder<T> {
    private T value;

    public Holder() {

    }

    public Holder(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public boolean equals(Object o) {
        return value.equals(o);
    }
}
