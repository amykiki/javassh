package com.github.amysue.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by Amysue on 2016/9/4.
 */
public class FreezeAlien {
    public static void main(String[] args) throws IOException {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(Util.testDir + "Alien.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
