package com.kazale.codetest.lru;

/**
 * Interface to manage a LRU (Least Recently Used) cache.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public interface LRUCache {

	/**
	 * Return a value from the cache to the given key.
	 * 
	 * @param key
	 * @return String value
	 */
	String get(String key);
	
	/**
	 * Put a new value in the cache to the given key.
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, String value);
	
	/**
	 * Return the max cache size.
	 * 
	 * @return max cache size.
	 */
	int getMaxSize();
	
	/**
	 * Return the current used cache size.
	 * 
	 * @return current used cache size.
	 */
	int getSize();
	
	/**
	 * Print in the System out the cached data.
	 */
	void printData();
}
