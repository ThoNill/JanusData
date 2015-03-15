package org.janus.data;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.janus.helper.DebugAssistent;

/**
 * 
 * Dient dazu ein Objekt einer Klasse erzeugen
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 */
public interface ClassFactory {
	static Logger logger = Logger.getLogger(ClassFactory.class.getSimpleName());

	
	@SuppressWarnings("rawtypes")
	Class getClass(String classname);
	
	InputStream getResource(String name);
	
	@SuppressWarnings("rawtypes")
	default Object getInstance(String classname, Class check) {
		DebugAssistent.doNullCheck(classname, check);
		Object o = getInstance(classname);
		if (!check.isInstance(o)) {
			logger.fatal("class [" + classname + "] is not of Type "
					+ check.getName());
			return null;
		}
		return o;
	}

	@SuppressWarnings("rawtypes")
	default Object getInstance(String classname) {
		DebugAssistent.doNullCheck(classname);

		Class cl = getClass(classname);
		if (cl != null) {
			return getInstance(cl);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	default Object getInstance(Class cl) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Create object of class [" + cl.getName() + "]");
			}
			return cl.newInstance();
		} catch (InstantiationException e) {
			logger.fatal("class [" + cl.getName() + "] can not be instantated");
		} catch (IllegalAccessException e) {
			logger.fatal("class [" + cl.getName()
					+ "] Illegal Access at instantiation");
		}
		return null;
	}

}
