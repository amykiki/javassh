package com.github.amysue.io;

import java.io.*;

/**
 * Created by Amysue on 2016/8/30.
 */
public class FileOutputShortCut {
    static String filename = "D:\\Java\\ebook\\test\\test1.txt";
    static String newfile  = "D:\\Java\\ebook\\test\\test2.txt";

    public static void main(String[] args) {
        BufferedReader br        = new BufferedReader(new StringReader(BufferedInputFile.read(filename)));
        PrintWriter    pw        = null;
        int            linecount = 1;
        String         s;
        try {
            pw = new PrintWriter(newfile);
            while ((s = br.readLine()) != null) {
                pw.println(linecount++ + ": " + s + 2020);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            pw.close();
        }
    }
}
