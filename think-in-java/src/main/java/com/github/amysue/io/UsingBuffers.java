package com.github.amysue.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by Amysue on 2016/9/2.
 */
public class UsingBuffers {
    private static void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2);
            buffer.put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        /*在上一步结束后 bytebuffer：
        * limit = 24
        * capacity = 24
        * position = 0
        * mark = -1
        * CharBuffer cb:
        * limit = 12
        * capacity = 12
        * postion = 12
        * mark = -1*/
        System.out.println(cb.rewind());
        /*在rewind结束后，ByteBuffer值不变，CharBuffer position = 0, 其他不变*/
        symmetricScramble(cb);
        System.out.println(cb.rewind());
        symmetricScramble(cb);
        System.out.println(cb.rewind());
    }
}
