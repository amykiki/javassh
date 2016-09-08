package com.amy.spring.boot.logging;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Amysue on 2016/9/8.
 */
public class LoggingBackTest {
    LoggingBack lb = new LoggingBack();
    LoggingBack2 lb2 = new LoggingBack2();
    @Test
    public void logOut() throws Exception {
        lb.LogOut();
        lb2.LogOut();
    }

}