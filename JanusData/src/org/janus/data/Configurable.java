package org.janus.data;

/**
 * 
 * A Configurable Element
 * 
 * Ein Objekt, das Konfiguriert werden kann, configure sollte idempotent sein
 * d.h. die doppelte Ausf�hrung ist erlaubt und �ndert nichts
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 * @see DataContext
 * 
 */
public interface Configurable {
    void configure(DataDescription description);
}
