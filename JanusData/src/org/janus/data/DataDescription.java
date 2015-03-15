package org.janus.data;

/**
 * Holt den Index eines Schlüsselnamens. Wenn der Schlüssel noch nicht existiert
 * wird das DataModel erweitert. Wenn ein DataModel fixiert ist, ist dies nicht
 * mehr erlaubt und es wird eine RuntimeException geworfen.
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 * @param name
 */
public interface DataDescription {

	boolean existsHandleName(String name);

	int getHandle(String name);

	String getHandleName(int i);

	int getSize();

	int createAnonymousHandle();

	DataContext newContext();

}