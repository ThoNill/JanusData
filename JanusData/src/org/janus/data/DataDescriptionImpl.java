package org.janus.data;

import java.util.ArrayList;

import org.janus.helper.DebugAssistent;

/**
 * 
 * Das DataModel legt die Feldnamen und Feldpositionen fest, die Werte der
 * Felder befinden sich in einem DataContext.
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 * @see DataContextImpl
 * 
 */
public class DataDescriptionImpl implements DataDescription {
	/**
	 * Die Feldnamen oder Schlüssel des DataModel.
	 */
	private ArrayList<String> list;

	/**
	 * Ein DataModel ist fixiert, wenn es nicht mehr geändert werden soll, da
	 * bereits ein DataContext mit diesem DataModel erzeugt wurde.
	 * 
	 */
	private boolean fixed = false;

	/**
	 * Zähler für Identitäten
	 * 
	 */
	private int identity = 0;

	public DataDescriptionImpl() {
		list = new ArrayList<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.janus.data.DataDescription#getKey(java.lang.String)
	 */
	@Override
	public synchronized int getHandle(String name) {
		DebugAssistent.doNullCheck(name);

		int i = list.indexOf(name);
		if (i < 0) {
			if (fixed) {
				throw new RuntimeException(
						"Datamodel is already fixed. Source= [" + name + "]");
			} else {
				list.add(name);
				return list.indexOf(name);
			}
		}
		return i;
	}

	/**
	 * Holt die Indexwerte mehrer Schlüssel
	 * 
	 * @param names
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.janus.data.DataDescription#hasKey(java.lang.String)
	 */
	@Override
	public boolean existsHandleName(String name) {
		DebugAssistent.doNullCheck(name);

		return list.contains(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.janus.data.DataDescription#getKey(int)
	 */
	@Override
	public String getHandleName(int i) {
		return list.get(i);
	}

	/**
	 * Wenn ein Objekt eine ID braucht, kann es sich einen von einem DataModel
	 * holen
	 * 
	 */
	public synchronized int newId() {
		if (fixed) {
			throw new RuntimeException("Datamodel is already fixed. No newId");
		}
		identity++;
		return identity;
	}

	/**
	 * Erzeugter Feldname zu einer ID
	 * 
	 */
	public String newFieldName() {
		return "@ID " + newId();
	}

	/**
	 * Legt einen Neuen Feldnamen über eine ID an.
	 * 
	 */
	@Override
	public int createAnonymousHandle() {
		return getHandle(newFieldName());
	}

	/**
	 * Fixiert ein DataModel. Nach dem fixieren kann das DataModel nicht mehr
	 * ergänzt werden
	 * 
	 */
	public void fix() {
		fixed = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.janus.data.DataDescription#getSize()
	 */
	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public DataContextImpl newContext() {
		return new DataContextImpl(this);
	}

	public boolean isFixed() {
		return fixed;
	}

}
