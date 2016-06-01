package org.mybatis.smvc.exception;

/**
 * Created by Amysue on 2016/5/31.
 */
public class SmvcException extends Exception {
    public SmvcException() {
        super();
    }

    public SmvcException(String message) {
        super(message);
    }

    public SmvcException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmvcException(Throwable cause) {
        super(cause);
    }

    protected SmvcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
