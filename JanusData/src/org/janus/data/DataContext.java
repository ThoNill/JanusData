package org.janus.data;

import java.io.Serializable;

import org.apache.log4j.Logger;

public interface DataContext {

    /**
     * 
     * Holt den Datenwert zu einem Index
     * 
     * @see DataDescription
     * 
     * @author Thomas Nill
     * 
     *         Lizenz GPLv3
     * 
     */
    Serializable getObject(int i);

    /**
     * 
     * Setzt den Datenwert zu einem Index
     * 
     * @see DataDescription
     * 
     */
    void setObject(int i, Serializable value);

    void debug(Logger log);

    DataDescription getDataDescription();

    default int getHandle(String name) {
        return getDataDescription().getHandle(name);
    }

}