package com.github.amysue.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by Amysue on 2016/8/31.
 */
public class TextFile extends ArrayList<String> {
    public static String read(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String         s;
            try {
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                br.close();
            }

        } catch (IOException e) {
            throw e;
        }
        return sb.toString();
    }

    public static void write(String filename, String text) throws IOException {
        try {
            PrintWriter pw = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                pw.print(text);
            } finally {
                pw.close();
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public TextFile(String filename, String splitter) throws IOException{
        super(Arrays.asList(read(filename).split(splitter)));
        if(get(0).equals("")) remove(0);
    }

    public TextFile(String filename) throws IOException{
        this(filename, "\n");
    }

    public void write(String filename) throws IOException{
        try {
            PrintWriter pw = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                for (String item : this) {
                    pw.println(item);
                }
            } finally {
                pw.close();
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws IOException{
        String srcFile = "D:\\IdealProj\\Leetcode\\src\\NQueens5102.java";
        String testFile = "D:\\Java\\ebook\\test\\test2.java";
        String file = read(srcFile);
        write(testFile, file);
        TreeSet<String> words = new TreeSet<String>(new TextFile(srcFile, "\\W+"));
        System.out.println(words.headSet("a"));
        System.out.println(words.tailSet("a"));
    }
}
