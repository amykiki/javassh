package com.github.amysue.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Amysue on 2016/8/30.
 */
public class UsingRandomAccessFile {
    static String filename = "D:\\Java\\ebook\\test\\data.txt";

    static void display() {
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filename, "r");
            for (int i = 0; i < 7; i++) {
                System.out.println("Value " + i + ": " + rf.readDouble());
            }
            System.out.println(rf.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rf != null) {
                try {
                    rf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filename, "rw");
            for (int i = 0; i < 7; i++) {
                rf.writeDouble(i * 1.414);
            }
            rf.writeUTF("这是文章的结束, this is the end of the file");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null) {
                    rf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        display();
        try {
            rf = new RandomAccessFile(filename, "rw");
            rf.seek(5 * 8);
            rf.writeDouble(47.00001);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null) {
                    rf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        display();
    }
}
