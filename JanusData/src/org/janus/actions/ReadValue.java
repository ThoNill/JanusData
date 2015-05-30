package org.janus.actions;

import java.io.Serializable;

import org.janus.data.DataContext;

public interface ReadValue {

	Serializable getObject(DataContext ctx);
}
