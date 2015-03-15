package org.janus.data;

import java.util.Vector;

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

	private static Vector<Configurable> collectMultipleOccurrencesTogether(
			Vector<?> liste) {
		DebugAssistent.doNullCheck(liste);

		Vector<Configurable> toInitialize = new Vector<Configurable>();
		for (Object configurable : liste) {
			if (configurable instanceof Configurable) {
				if (!toInitialize.contains(configurable)) {
					toInitialize.add((Configurable) configurable);
				}
			}
		}
		return toInitialize;
	}

	public static void configure(DataDescription description, Vector<?> liste) {
		DebugAssistent.doNullCheck(description, liste);

		Vector<Configurable> toInitialize = collectMultipleOccurrencesTogether(liste);
		for (Configurable configurable : toInitialize) {
			configurable.configure(description);
		}
	}
}
