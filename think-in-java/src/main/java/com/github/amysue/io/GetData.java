package com.github.amysue.io;

import java.nio.ByteBuffer;

/**
 * Created by Amysue on 2016/9/1.
 */
public class GetData {
    private static final int BSIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0;
        while (i++ < bb.limit()) {
            if (bb.get() != 0) {
                System.out.println("nonzero");
            }
        }
        System.out.println("i = " + i);
        bb.rewind();
        String s = "abcdefghijklmnopqrstuvwxyz98&^%$";
        System.out.println(s.length());
        bb.asCharBuffer().put(s);
        char c;
        while ((c = bb.getChar()) != 0) {
            System.out.print(c + " ");
        }
        bb.rewind();
        bb.asShortBuffer().put(Short.MAX_VALUE);
        System.out.println();
        System.out.println(bb.getShort());
        bb.rewind();
        bb.asShortBuffer().put((short) 471142); //超出short最大值，截掉
        System.out.println(bb.getShort());
        bb.rewind();
        bb.asIntBuffer().put(Integer.MAX_VALUE);
        bb.asIntBuffer().put(1, 99471142);
        System.out.println(bb.getInt(4));
        System.out.println(bb.getInt(0));
        bb.rewind();
        bb.asLongBuffer().put(33147483647l);
        System.out.println(bb.getLong());
        bb.rewind();
        bb.asFloatBuffer().put(944432875.8f);
        bb.asFloatBuffer().put(1, 1f);
        System.out.println(bb.getFloat());
        System.out.println(bb.getFloat(4));//注意float是32位
        bb.rewind();
        bb.asDoubleBuffer().put(9947114245d);
        System.out.println(bb.getDouble());
    }
}
