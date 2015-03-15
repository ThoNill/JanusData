package org.janus.actions;

import org.janus.data.Configurable;
import org.janus.data.DataContext;

/**
 * 
 * A Action on the values of a DataContext or the check of a rule.
 * 
 * Aktion oder Prüfung anhand der Werte eines DataCOntext
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 * @see DataContext
 * 
 */
public interface Action extends Configurable {

	/**
	 * 
	 * Execution of a action or the check of a rule
	 * 
	 * @param context
	 */
	void perform(DataContext context);
}
