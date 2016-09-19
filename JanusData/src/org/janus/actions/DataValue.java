package org.janus.actions;

import org.janus.data.Configurable;

public interface DataValue extends ReadValue, WriteValue, Configurable {

    DataFormat getFormat();

    void setFormat(DataFormat format);

}