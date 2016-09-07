package com.github.amysue.io;

import java.io.*;

/**
 * Created by Amysue on 2016/9/4.
 */
public class Blip4 extends Blip3 {
    private String ss;
    private int ii;

    public Blip4() {
        super(16, "amysue");
        System.out.println("Blip4 Default Constructor");
        ii = 2016;
    }

    public Blip4(int i, String s, int ii, String ss) {
        super(i, s);
        System.out.println("Blip4(int i, String s, String ss)");
        this.ii = ii;
        this.ss = ss;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + ii + ": " + ss;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip4.writeExternal");
        super.writeExternal(out);
//        out.writeInt(ii);
        out.writeObject(ss);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip4.readExternal");
        super.readExternal(in);
//        ii = in.readInt();
        ss = (String) in.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        String objfile = Util.testDir + "blip4.obj";
        System.out.println("Construct Object:");
        Blip4 blip4 = new Blip4(20, "kevin", 1987, "amysue");
        System.out.println(blip4);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(objfile));
        System.out.println("Saving blip4:");
        out.writeObject(blip4);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(objfile));
        System.out.println("Recovering Blip4:");
        blip4 = (Blip4)in.readObject();
        System.out.println(blip4);
    }

}
