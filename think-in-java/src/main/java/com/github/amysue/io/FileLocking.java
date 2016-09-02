package com.github.amysue.io;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amysue on 2016/9/2.
 */
public class FileLocking {
    public static void main(String[] args) throws Exception{
        FileOutputStream fos = new FileOutputStream("out.txt", true);
        FileLock fl = fos.getChannel().tryLock();
        if (fl != null) {
            System.out.println("Locked File");
            TimeUnit.SECONDS.sleep(20);
            fl.release();
        }
        fos.close();
    }
}
