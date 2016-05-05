package doc.exception;

/**
 * Created by Amysue on 2016/5/4.
 */
public class DocException extends Exception {
    public DocException() {
        super();
    }

    public DocException(String message) {
        super(message);
    }

    public DocException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocException(Throwable cause) {
        super(cause);
    }

    protected DocException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
