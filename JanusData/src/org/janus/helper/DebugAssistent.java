package org.janus.helper;

import org.apache.log4j.Logger;

public class DebugAssistent {
    static Logger logger = Logger.getLogger(DebugAssistent.class
            .getSimpleName());

    public static void log(Logger log, Exception ex) {

        StackTraceElement elements[] = ex.getStackTrace();
        log.error("Exception [" + ex.getMessage() + "]");
        for (int i = 0; i < elements.length; i++) {
            log.error("[" + elements[i] + "] " + elements[i].getFileName()
                    + ":" + elements[i].getMethodName() + ":"
                    + elements[i].getLineNumber());
        }

    }

    public static void log(Exception ex) {
        log(logger, ex);
    }

    public static void doNullCheck(Object... objects) {
        for (Object o : objects) {
            if (o == null) {
                throw new IllegalArgumentException("Must be not Null!");
            }
        }
    }

    public static void doNullCheckAndOfType(Class cl, Object... objects) {
        doNullCheck(cl);
        doNullCheck(objects);
        for (Object o : objects) {
            if (!cl.isInstance(o)) {
                throw new IllegalArgumentException("Must be a " + cl.getName());
            }
        }
    }

}
