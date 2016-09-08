package com.amy.spring.boot.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Amysue on 2016/9/8.
 */
public class LoggingBack2 {
    private static Logger logger = LoggerFactory.getLogger(LoggingBack2.class);

    public void LogOut() {
        logger.debug("LoggingBack2.debug()...");
        logger.info("LoggingBack2.info()...");
        logger.error("LoggingBack2.error()...");
    }
}
