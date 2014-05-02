package org.lp20.aikma.storage;

import java.io.InputStream;

public interface DataStore {
	/**
	 * Retrieve a data item from the storage.
	 * @param identifier Unique identifier for the data item to retrieve.
	 * @return An InputStream for the identified data.
	 */
	public InputStream load(String identifier);
	
	/**
	 * Store a data item to the storage.
	 * @param identifier Unique identifier for the data item to store.
	 * @param data An InputStream containing the data.
	 */
	public void store(String identifier, InputStream data);
}