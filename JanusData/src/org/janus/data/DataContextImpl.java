package org.janus.data;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.janus.helper.DebugAssistent;

/**
 * 
 * Ein DataContext ist nicht viel mehr als eine Reihe von Datenwerten. Die
 * Beschreibung der Datenwerte befindet sich im DataModel.
 * 
 * Ein DataContext enthält z.B. die Datenwerte einer Sitzung und besitzt eine
 * einfache Historie, die es erlaubt festzustellen, ob siche ein Datenwert
 * geändert hat.
 * 
 * @author THOMAS NILL
 * 
 *         Lizenz GPLv3
 * 
 * @see DataDescriptionImpl
 * 
 */
public class DataContextImpl implements DataContext {

    /**
     * Die aktuellen Datenwerte der Felder eines DataModel
     */
    private Serializable[] current;

    /**
     * Das DataModel für die Datenwerte.
     * 
     * @see DataDescriptionImpl
     */
    private DataDescription model;

    /**
     * Anlegen eines DataContext aus den Angaben im DataModel.
     * 
     * Nach der Anlage des ersten DataCOntext zu einem DataModel kann das
     * DataModel nicht mehr erweitert werden.
     * 
     * @see DataDescription
     * 
     */
    public DataContextImpl(DataDescription model) {
        DebugAssistent.doNullCheck(model);

        this.model = model;
        if (model instanceof DataDescriptionImpl) {
            ((DataDescriptionImpl) model).fix(); // DataModel soll nicht mehr
                                                 // geändert werden
        }
        int l = model.getSize();
        current = new Serializable[l];
    }

    @Override
    public DataDescription getDataDescription() {
        return model;
    }

    /*
     * (non-Javadoc)
     * 
     * @see test.janus.data.DataContext#getObject(int)
     */
    @Override
    public Serializable getObject(int i) {
        return current[i];
    }

    /*
     * (non-Javadoc)
     * 
     * @see test.janus.data.DataContext#setObject(int, java.lang.Object)
     */
    @Override
    public void setObject(int i, Serializable value) {
        current[i] = value;
    }

    /**
     * Setzt mehrere Datenwerte, indexes ist eine Reihe von Indexwerten.
     * 
     * @see DataDescriptionImpl
     * 
     */

    public void setObjects(int[] indexes, Serializable[] values) {
        if (indexes.length != values.length) {
            throw new IllegalArgumentException(
                    "index and value Array has different length");
        }
        for (int i = indexes.length - 1; i >= 0; i--) {
            current[indexes[i]] = values[i];
        }
    }

    /**
     * Debug all values
     * 
     * Prüfausgabe aller Werte.
     * 
     */
    @Override
    public void debug(Logger log) {
        DebugAssistent.doNullCheck(log);

        if (!log.isDebugEnabled()) {
            return;
        }

        int count = model.getSize();
        for (int i = 0; i < count; i++) {
            log.debug("" + model.getHandleName(i) + "= [" + current[i] + "]");
        }
    }

    /**
     * Prüfausgabe bestimmter Werte. indexes ist eine Reihe von Indexen
     * 
     */
    public void debug(Logger log, int... indexes) {
        DebugAssistent.doNullCheck(log);

        if (!log.isDebugEnabled()) {
            return;
        }
        for (int j : indexes) {
            log.debug("" + model.getHandleName(j) + "= [" + current[j] + "]");
        }
    }

}
