package com.github.amysue.io;

import java.io.*;

/**
 * Created by Amysue on 2016/9/4.
 */
class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Consturctor");
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExteneral");
    }
}

class Blip2 implements Externalizable {
    /*During deserialization, the fields of non-serializable classes will be initialized
    using the public or protected no-arg constructor of the class.
    A no-arg constructor must be accessible to the subclass that is serializable.
    The fields of serializable subclasses will be restored from the stream.*/
    Blip2() {
        System.out.println("Blip2 Consturctor");
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExteneral");
    }
}
public class Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        String objfile = Util.testDir + "blip.obj";
        System.out.println("Constructing objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(objfile));
        System.out.println("Saving Objects");
        out.writeObject(b1);
        out.writeObject(b2);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(objfile));
        System.out.println("Recovering b1:");
        b1 = (Blip1) in.readObject();
        System.out.println("Recovering b2");
        b2 = (Blip2) in.readObject();
        System.out.println(1);
    }
}
