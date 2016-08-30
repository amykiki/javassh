package com.github.amysue.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Amysue on 2016/8/29.
 */
public class BufferedInputFile {
    public static String read(String filename) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String        s;
            StringBuilder sb = new StringBuilder();
            while ((s = in.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String filename = "D:\\Java\\ebook\\test\\test1.txt";
        System.out.println(read(filename));
    }
}
