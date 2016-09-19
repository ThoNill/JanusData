package org.janus.data;

import java.util.ArrayList;
import java.util.List;

import org.janus.helper.DebugAssistent;

/**
 * Soll mehrfaches Konfigurieren in einer Liste von Configurable Objekten
 * verhindern.
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 * @see DataContext
 * 
 */
public class ConfigurableHelper {

    public ConfigurableHelper() {
        super();
    }

    private static List<Configurable> collectMultipleOccurrencesTogether(
            List<?> liste) {
        DebugAssistent.doNullCheck(liste);

        List<Configurable> toInitialize = new ArrayList<Configurable>();
        for (Object configurable : liste) {
            if (configurable instanceof Configurable
                    && !toInitialize.contains(configurable)) {
                toInitialize.add((Configurable) configurable);
            }
        }
        return toInitialize;
    }

    public static void configure(DataDescription description, List<?> liste) {
        DebugAssistent.doNullCheck(description, liste);

        List<Configurable> toInitialize = collectMultipleOccurrencesTogether(liste);
        for (Configurable configurable : toInitialize) {
            configurable.configure(description);
        }
    }
}
