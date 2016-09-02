package com.github.amysue.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by Amysue on 2016/9/2.
 */
public class CreatSrt {
    private String getSrtName(File file) throws IOException {
        String filename = file.getName();
        String srtName  = file.getParent() + "\\" + filename.substring(0, filename.indexOf(".")) + ".srt";
        System.out.println(srtName);
        return srtName;
    }

    private void generate(File srcFile) throws IOException {
        File        destFile = new File(getSrtName(srcFile));
        FileChannel in       = new FileInputStream(srcFile).getChannel();
        FileChannel out      = new FileOutputStream(destFile).getChannel();

        ByteBuffer bb       = ByteBuffer.allocate(1024);
        String     encoding = System.getProperty("file.encoding");
        ByteBuffer ob       = Charset.forName(encoding).encode("1");
        byte       start    = ob.get();
        in.read(bb);
        bb.flip();
        int sp = 0;
        while (bb.hasRemaining()) {
            if (bb.get() == start) {
                sp = bb.position() - 1;
                break;
            }
        }
        bb.position(sp);
        out.write(bb);
        bb.clear();
        while (in.read(bb) != -1) {
            bb.flip();
            out.write(bb);
            bb.clear();
        }
        out.close();
        in.close();
    }

    private void walk(File file) throws IOException{
        if (file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            File[] files = file.listFiles();
            for (File file1 : files) {
                walk(file1);
            }
        } else {
            String name = file.getName();
            int beginIndex = name.length() - 4;
            if (beginIndex > 0 && name.substring(beginIndex).equals(".vtt")) {
                generate(file);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CreatSrt cs       = new CreatSrt();
        File file = new File("D:\\LearnHowToLearn\\week2");
        cs.walk(file);
    }
}
