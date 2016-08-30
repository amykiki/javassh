package com.github.amysue.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by Amysue on 2016/8/30.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) {
        byte[] bytes = BufferedInputFile.read("D:\\Java\\ebook\\test\\test1.txt").getBytes();
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        int c1;
        while ((c1 = bi.read()) != -1) {
            System.out.print(c1 + " ");
        }
        System.out.println();
        DataInputStream din = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            while (true) {
                System.out.print((char)din.readByte() + " ");
            }
        } catch (EOFException e) {
            System.out.println("End Of Stream");
        } catch (IOException e) {
            e.printStackTrace();
        }
        din = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            while (true) {
                System.out.print((char)din.readChar() + " ");
            }
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                din.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
