package org.janus.data;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import org.janus.helper.DebugAssistent;


/**
 * 
 * Dient dazu ein Objekt einer Klasse erzeugen
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 */
public enum DefaultClassFactory implements ClassFactory{
	FACTORY;
	

	@Override
	@SuppressWarnings("rawtypes")
	public Class getClass(String classname) {
		Class c = null;
		try {
			c = Thread.currentThread().getContextClassLoader()
					.loadClass(classname);
		} catch (ClassNotFoundException e1) {
			try {
				c = DefaultClassFactory.class.getClassLoader().loadClass(classname);
			} catch (ClassNotFoundException e2) {
				logger.fatal("class [" + classname + "] not found");
			}
		}
		return c;
	}



	@Override
	public InputStream getResource(String name) {
		DebugAssistent.doNullCheck(name);

		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(name);
		if (in == null) {
			in = DefaultClassFactory.class.getClassLoader().getResourceAsStream(name);
		}
		if (in == null) {
			try {
				in = new FileInputStream(name);
			} catch (FileNotFoundException e) {
				throw new RuntimeException("Resource " + name
						+ " konnte nicht gefunden werden!");
			}
		}
		return in;
	}
}
