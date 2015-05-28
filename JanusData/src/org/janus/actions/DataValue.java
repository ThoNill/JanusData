package org.janus.actions;

import org.janus.data.Configurable;
import org.janus.single.ObjectCreator;

public interface DataValue extends ReadValue, WriteValue, Configurable{

//	ObjectCreator getCreator();
//	void setCreator(ObjectCreator type);
	

	DataFormat getFormat();
	void  setFormat(DataFormat format);

}