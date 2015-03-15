package org.janus.data;

/**
 * Message berechnen aus einem DataContext Textwerte
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 */
public interface Message {
	String getMessage(DataContext ctx);
}
