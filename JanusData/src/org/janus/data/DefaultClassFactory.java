package org.janus.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.MissingResourceException;

import org.janus.helper.DebugAssistent;

/**
 * 
 * Dient dazu ein Objekt einer Klasse erzeugen
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 */
public enum DefaultClassFactory implements ClassFactory {
	
	FACTORY(false),
	FACTORY_EXCEPTION(true);
	
	private boolean check;
	
	private DefaultClassFactory(boolean check) {
		this.check = check;
	}

    @Override
    @SuppressWarnings("rawtypes")
    public Class getClass(String classname) {
        Class c = null;
        try {
            c = Thread.currentThread().getContextClassLoader()
                    .loadClass(classname);
        } catch (ClassNotFoundException e1) {
            try {
                LOG.debug(e1);
                c = DefaultClassFactory.class.getClassLoader().loadClass(
                        classname);
            } catch (ClassNotFoundException e2) {
                if (check) {
                	LOG.fatal("class [" + classname + "] not found", e2);
                }
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
            in = DefaultClassFactory.class.getClassLoader()
                    .getResourceAsStream(name);
        }
        if (in == null) {
            try {
                return new FileInputStream(name);
            } catch (FileNotFoundException e) {
                MissingResourceException ex = new MissingResourceException(
                        "Resource " + name + " konnte nicht gefunden werden!",
                        name, name);
                ex.initCause(e);
                throw ex;
            }
        }
        return in;
    }
}
