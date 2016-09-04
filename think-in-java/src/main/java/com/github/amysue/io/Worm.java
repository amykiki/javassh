package com.github.amysue.io;

import sun.awt.Win32ColorModel24;

import javax.management.ObjectInstance;
import java.io.*;
import java.util.Random;

/**
 * Created by Amysue on 2016/9/3.
 */
class Data implements Serializable{
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}
public class Worm implements Serializable {
    private static Random rand = new Random(47);
    private Data[] d = {
        new Data(rand.nextInt(10)), new Data(rand.nextInt(10)), new Data(rand.nextInt(10))
    };
    private Worm next;
    private char c;

    public Worm(int i, char x) {
        System.out.println("Worm constuctor " + i);
        c = x;
        if (--i > 0) {
            next = new Worm(i, (char) (x + 1));
        }
    }

    public Worm() {
        System.out.println("Defalut Constructor");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data dat : d) {
            result.append(dat);
        }
        result.append(")");
        if (next != null) {
            result.append(next);
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception{
        Worm w = new Worm(6, 'a');
        System.out.println("w = " + w);
        String objfile = "D:\\Java\\ebook\\test\\obj";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(objfile));
        out.writeInt(1);
        out.writeObject("write object to file");
        out.writeObject(w);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(objfile));
        int num1 = in.readInt();
        String s1 = (String) in.readObject();
        Worm w1 = (Worm)in.readObject();
        System.out.println(num1 + ":" + s1 + "w1 = " + w1);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeInt(2);
        out2.writeObject("write object to bytearry");
        out2.writeObject(w1);
        out2.flush();
        out2.close();
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        int num2 = in2.readInt();
        String s2 = (String) in2.readObject();
        Worm w2 = (Worm)in2.readObject();
        System.out.println(num2 + ":" + s2 + "w2= " + w2);
    }
}
