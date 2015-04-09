package org.janus.actions;

import java.io.Serializable;

public interface DataType {
	String convert2String(Object obj);

	Serializable createInitialization();
}
