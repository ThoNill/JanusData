package org.janus.actions;

import java.io.Serializable;

import org.janus.data.DataContext;

public interface WriteValue {
	void setObject(DataContext ctx, Serializable value);
}
