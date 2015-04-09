package org.janus.actions;

import java.io.Serializable;

import org.janus.data.DataContext;
import org.janus.data.DataDescription;

public interface DataValue {

	DataType getType();

	DataValue setType(DataType type);

	String getName();

	void configure(DataDescription description);

	void setObject(DataContext ctx, Serializable value);

	Serializable getObject(DataContext ctx);

}