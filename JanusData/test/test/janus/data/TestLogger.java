package test.janus.data;

import java.util.Vector;

import org.apache.log4j.Logger;

public class TestLogger extends Logger {
	Vector<String> log = new Vector<>();
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
		log.addElement(message.toString());
	}

	public void setDebugEnabled(boolean debug) {
		this.debug = debug;
	}

	public Vector<String> getLog() {
		return log;
	}
}
