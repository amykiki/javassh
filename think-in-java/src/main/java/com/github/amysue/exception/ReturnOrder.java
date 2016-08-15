package com.github.amysue.exception;

/**
 * Created by Amysue on 2016/8/7.
 */
public class ReturnOrder {
    public int test() throws Exception{
        Exception e = null;
        try {
            System.out.println("Begin Fun1");
            return fun1();
        } catch (NullPointerException e1) {
            e = e1;
        } finally {
            System.out.println("Begin Fun2");
            throw e;
//            return fun2();
        }
    }

    public int test2() {
        int x = 1;
        try {
            return x;
        } finally {
            ++x;
            System.out.println("x = " + x);
        }
    }

    public int fun1() throws NullPointerException{
        System.out.println("Fun1 End");
        return 1;
    }

    public int fun2() {
        System.out.println("Fun2 End");
        return 2;
    }

    public static void main(String[] args) throws Exception{
        ReturnOrder ro = new ReturnOrder();
        int result = ro.test();
        System.out.println("result = " + result);
        int result2 = ro.test2();
        System.out.println("result2 = " + result2);
    }
}
