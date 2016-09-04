package com.github.amysue.io.xfiles;

import com.github.amysue.io.Util;

import java.io.*;

/**
 * Created by Amysue on 2016/9/4.
 */
public class ThawAlien {
    public static void main(String[] args) throws IOException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(Util.testDir + "Alien.file"));
        try {
            Object mystery = in.readObject();
            System.out.println(mystery.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
/*
* 在本例中没有报错，因为Alien.class 在classpath中，但是如果把这个文件移除，到D:\IdealProj\SSH，这时运行就会报错
* 以下是输出
* java.lang.ClassNotFoundException: com.github.amysue.io.Alien
        at java.net.URLClassLoader.findClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at sun.misc.Launcher$AppClassLoader.loadClass(Unknown Source)
        at java.lang.ClassLoader.loadClass(Unknown Source)
        at java.lang.Class.forName0(Native Method)
        at java.lang.Class.forName(Unknown Source)
        at java.io.ObjectInputStream.resolveClass(Unknown Source)
        at java.io.ObjectInputStream.readNonProxyDesc(Unknown Source)
        at java.io.ObjectInputStream.readClassDesc(Unknown Source)
        at java.io.ObjectInputStream.readOrdinaryObject(Unknown Source)
        at java.io.ObjectInputStream.readObject0(Unknown Source)
        at java.io.ObjectInputStream.readObject(Unknown Source)
        at ThawAlien.main(ThawAlien.java:12)
*/