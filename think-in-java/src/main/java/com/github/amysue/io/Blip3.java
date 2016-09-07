package com.github.amysue.io;

import java.io.*;

/**
 * Created by Amysue on 2016/9/4.
 */
public class Blip3 implements Externalizable {
    private int i;
    private String s;

    public Blip3() {
        System.out.println("Blip3 Default Constructor");
    }

    public Blip3(int i, String s) {
        System.out.println("Blip3(int i, String s)");
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return i + ":" + s;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        out.writeInt(i);
        out.writeObject(s);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        i = in.readInt();
        s = (String) in.readObject();
    }

    public static void main(String[] args)throws IOException, ClassNotFoundException{
        String objfile = Util.testDir + "blip3.obj";
        System.out.println("Construct Object:");
        Blip3 b3 = new Blip3(16, "Amy Sue");
        System.out.println(b3);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(objfile));
        System.out.println("Saving Object:");
        out.writeObject(b3);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(objfile));
        System.out.println("Recovering b3:");
        b3 = (Blip3)in.readObject();
        System.out.println(b3);
    }
}
