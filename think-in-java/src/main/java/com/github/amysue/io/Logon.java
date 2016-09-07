package com.github.amysue.io;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/9/4.
 */
public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;

    public Logon(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Logon{" +
                "date=" + date +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception{
        String objfile = Util.testDir + "obj";
        Logon a = new Logon("Hulk", "myLittlePony");
        System.out.println("Logon a = " + a);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(objfile));
        out.writeObject(a);
        out.close();
        TimeUnit.SECONDS.sleep(1);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(objfile));
        System.out.println("Recovering object a " + new Date());
        a = (Logon)in.readObject();
        System.out.println("Logon a = " + a);
    }
}
