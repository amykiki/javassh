package com.github.amysue.io;

import java.io.*;

/**
 * Created by Amysue on 2016/8/30.
 */
public class BasicFileOutput {
    static String filename = "D:\\Java\\ebook\\test\\test1.txt";
    static String newfile  = "D:\\Java\\ebook\\test\\test2.txt";
    static String newfile2 = "D:\\Java\\ebook\\test\\test3.txt";

    public static void main(String[] args) {
        BufferedReader br        = new BufferedReader(new StringReader(BufferedInputFile.read(filename)));
        FileWriter     fw        = null;
        int            linecount = 1;
        String         s;
        try {
            fw = new FileWriter(newfile);
            while ((s = br.readLine()) != null) {
                fw.write(linecount++ + ": " + s);
                fw.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw = null;
        linecount = 1;
        br        = new BufferedReader(new StringReader(BufferedInputFile.read(filename)));
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(newfile2)));
            while ((s = br.readLine()) != null) {
                pw.println(linecount++ + ": " + s + 2020);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
