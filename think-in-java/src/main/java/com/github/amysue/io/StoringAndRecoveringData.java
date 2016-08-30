package com.github.amysue.io;

import java.io.*;

/**
 * Created by Amysue on 2016/8/30.
 */
public class StoringAndRecoveringData {
    public static void main(String[] args) {
        String filename = "D:\\Java\\ebook\\test\\data.txt";
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            out.writeDouble(3.14159);
            out.writeUTF("这是中文字，试试！！！");
            out.writeChar('A');
            out.writeInt(99);
            out.writeUTF("this is english charsascii");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        DataInputStream in = null;
        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
            System.out.println(in.readDouble());
            System.out.println(in.readUTF());
            System.out.println(in.readChar());
            System.out.println(in.readInt());
            System.out.println(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
