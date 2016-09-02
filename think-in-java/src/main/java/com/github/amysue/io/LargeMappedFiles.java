package com.github.amysue.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Amysue on 2016/9/2.
 */
public class LargeMappedFiles {
    static int length = 0x8ffffff;

    public static void main(String[] args) throws IOException{
        MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
        for(int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }
        System.out.println("Finished Writing");
        for(int i = length/2; i < length/2 + 6; i++) {
            System.out.println((char)out.get(i));
        }
    }
}
