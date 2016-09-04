package com.github.amysue.io;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Amysue on 2016/9/3.
 */
public class GZIPCompress {
    public static void main(String[] args) throws IOException{
        String dir = "D:\\Java\\ebook\\test\\";
        String infile = dir + "testcompress.txt";
        String outfile = dir + "compress.gz";

        /*BufferedReader br = new BufferedReader(new FileReader(infile)); //这个方法，对于文件中含有非ascii码的文本不行，因为不能解码，不能直接用Fileeader解码
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        br.close();*/
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(infile)); //用byte没有编码要求
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(outfile)));
        System.out.println("Writing Compress " + infile + " to " + outfile);
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(outfile))));
        String s;
        while ((s = in2.readLine()) != null) {
            System.out.println(s);
        }
        in2.close();
    }
}
