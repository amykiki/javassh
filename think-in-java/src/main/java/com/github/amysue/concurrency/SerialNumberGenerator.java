package com.github.amysue.concurrency;

/**
 * Created by Amysue on 2016/8/10.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public synchronized static int nextSerialNumber() {
        return serialNumber++;
    }
}
