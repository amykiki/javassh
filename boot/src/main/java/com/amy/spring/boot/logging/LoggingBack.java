package com.amy.spring.boot.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Amysue on 2016/9/8.
 */
public class LoggingBack {
    private static Logger logger = LoggerFactory.getLogger(LoggingBack.class);

    public void LogOut() {
        logger.debug("LoggingBack.debug()...");
        logger.info("LoggingBack.info()...");
        logger.warn("LoggingBack.warn()...");
        logger.error("LoggingBack.error()...");
    }
}
