package com.github.amysue.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by Amysue on 2016/9/1.
 */
public class BufferToText {
    private static final int BSIZE = 1024;
    public static String dir = "D:\\Java\\ebook\\test";
    public static void main(String[] args) throws Exception{
        String outfile = "out.txt";
        FileChannel fc = new FileOutputStream(outfile).getChannel();
        fc.write(ByteBuffer.wrap("this is written by fc\n".getBytes()));
        fc.write(ByteBuffer.wrap("这是一句中文".getBytes()));
        ByteBuffer inBuff = ByteBuffer.allocate(100);
        inBuff.asCharBuffer().put("some more text");
//        注意这里不要用asCharBuffer方式来put中文，因为编码会有问题
//        inBuff.asCharBuffer().put("中文");
        fc.write(inBuff);
        fc.close();
        fc = new FileInputStream(outfile).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        System.out.println(buff.asCharBuffer());
        buff.rewind();
        while (buff.hasRemaining()) {
            System.out.print((char) buff.get());
        }
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        //注意在有中文的情况下，必须用这种方式才能解析
        System.out.println("\nDecoded using " + encoding + ": " + Charset.forName(encoding).decode(buff));
        fc.close();

    }
}
