package org.janus.actions;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;

public interface DataValue {

	DataType getType();

	DataValue setType(DataType type);

	String getName();

	void configure(DataDescription description);

	void setObject(DataContext ctx, Object value);

	Object getObject(DataContext ctx);

}