package com.github.amysue.interfaces;

/**
 * Created by Amysue on 2016/6/30.
 */
public interface InterfaceJava8 {
    void printSelf();
    default void printStr(String str) {
        if (!isNull(str)) {
            System.out.println("Java8 default method print:: " + str);
        } else {
            System.out.println("empty string");
        }
    }
    static boolean isNull(String str) {
        System.out.println("Interfac IsNull Check");
        return str == null ? true : str.trim().equals("") ? true : false;
    }
}
