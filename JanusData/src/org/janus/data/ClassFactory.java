package org.janus.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
    public static final Logger LOG = Logger.getLogger(ClassFactory.class
            .getSimpleName());

    @SuppressWarnings("rawtypes")
    Class getClass(String classname);

    InputStream getResource(String name);

    @SuppressWarnings("rawtypes")
    default Object getInstance(String classname, Class check) {
        DebugAssistent.doNullCheck(classname, check);
        Object o = getInstance(classname);
        if (!check.isInstance(o)) {
            LOG.fatal("class [" + classname + "] is not of Type "
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
            if (LOG.isDebugEnabled()) {
                LOG.debug("Create object of class [" + cl.getName() + "]");
            }
            return cl.newInstance();
        } catch (InstantiationException e) {
            LOG.fatal("class [" + cl.getName() + "] can not be instantated", e);
        } catch (IllegalAccessException e) {
            LOG.fatal("class [" + cl.getName()
                    + "] Illegal Access at instantiation", e);
        }
        return null;
    }

    /*
     * Erzeugt und pr�ft eine Instanz einer Klasse
     */
    default Object createAndCheckInstance(String classname, Class checkClass) {
        Object o = getInstance(classname);
        if (!checkClass.isInstance(o)) {
            LOG.fatal("class [" + classname + "] is not of Type "
                    + checkClass.getName());
            return null;
        }
        return o;
    }

    /*
     * Holt einen Reader aus den Resourcen
     */
    default Reader getReader(String resname) {
        return new BufferedReader(new InputStreamReader(getResource(resname)));
    }

}
