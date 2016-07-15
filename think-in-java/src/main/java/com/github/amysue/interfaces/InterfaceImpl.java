package com.github.amysue.interfaces;

/**
 * Created by Amysue on 2016/6/30.
 */
public class InterfaceImpl implements InterfaceJava8 {
    @Override
    public void printSelf() {
        System.out.println("This is " + getClass().getName());
    }
    public boolean isNull(String str) {
        System.out.println("This is class isNull Impl");
        return false;
    }
}
