package com.github.amysue.io;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Amysue on 2016/8/30.
 */
public class MemoryInput {
    public static void main(String[] args) {
        StringReader in = new StringReader(BufferedInputFile.read("D:\\Java\\ebook\\test\\test1.txt"));
        int c;
        try {
            while ((c = in.read()) != -1) {
                System.out.print((char)c + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
