package org.janus.actions;

public class ActionPerformException extends RuntimeException {

    public ActionPerformException() {
    }

    public ActionPerformException(String message) {
        super(message);
    }

    public ActionPerformException(Throwable cause) {
        super(cause);
    }

    public ActionPerformException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionPerformException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
