package com.github.amysue.io;

import java.io.*;

/**
 * Created by Amysue on 2016/8/31.
 */
public class Redirecting {
    public static void main(String[] args) throws IOException{
        String sdir = "D:\\IdealProj\\Leetcode\\src\\";
        String src = sdir + "MergeSortArray088.java";
        String odir = "D:\\Java\\ebook\\test\\";
        String dest = odir + "output.java";
        String edest = odir + "err.txt";
        PrintStream console = System.out;
        InputStream consolein = System.in;
        BufferedInputStream nin = new BufferedInputStream(new FileInputStream(src));
        PrintStream nout = new PrintStream(new BufferedOutputStream(new FileOutputStream(dest)));
//        PrintStream nerr = new PrintStream(new BufferedOutputStream(new FileOutputStream(edest)), true); //自动刷新
        PrintStream nerr = new PrintStream(new BufferedOutputStream(new FileOutputStream(edest)));
        System.setIn(nin);
        System.setOut(nout);
        System.setErr(nerr);
        String s;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        nout.close(); //再定义之前的out stream时，autoflush 为false，因此不会自动刷新，需要调用close()或者flush()来刷新
        System.setOut(console);
        nin.close();
        System.err.println("This is wrong file");
        System.err.println("99diifdf");
        System.setIn(consolein);
        br = new BufferedReader(new InputStreamReader(System.in));
        while ((s = br.readLine()) != null && s.length() != 0) {
            System.out.println(s);
            System.err.println(s);
        }
        br.close();
        nerr.close(); //同理见上
    }
}
