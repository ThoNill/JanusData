package test.janus.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class TestLogger extends Logger {
    List<String> log = new ArrayList<>();
    boolean debug = true;

    public TestLogger() {
        super("root");
    }

    @Override
    public boolean isDebugEnabled() {
        return debug;
    }

    @Override
    public void debug(Object message) {
        log.add(message.toString());
    }

    public void setDebugEnabled(boolean debug) {
        this.debug = debug;
    }

    public List<String> getLog() {
        return log;
    }
}
