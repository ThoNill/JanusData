package org.janus.data;

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
	Object getObject(int i);

	/**
	 * 
	 * Setzt den Datenwert zu einem Index
	 * 
	 * @see DataDescription
	 * 
	 */
	void setObject(int i, Object value);

	void debug(Logger log);

	DataDescription getDataDescription();

}