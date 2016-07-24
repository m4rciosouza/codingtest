package com.kazale.codetest.lru;

import com.kazale.codetest.lru.impl.LRUCacheImpl;

import junit.framework.TestCase;

/**
 * Test class for a LRU (Least Recently Used) cache.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class LRUCacheTest extends TestCase {

	private static final int DEFAULT_CACHE_MAX_SIZE = 100;
	private LRUCache lruCache;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test the default max cache size.
	 */
	public void testDefaultCacheSize() {
		this.lruCache = new LRUCacheImpl();
		
		assertEquals(DEFAULT_CACHE_MAX_SIZE, this.lruCache.getMaxSize());
	}
	
	/**
	 * Test custom max cache size.
	 */
	public void testCustomCacheSize() {
		int maxCacheSize = 10;
		
		this.lruCache = new LRUCacheImpl(maxCacheSize);
		
		assertEquals(maxCacheSize, this.lruCache.getMaxSize());
	}
	
	/**
	 * Test put an element in the LRU Cache.
	 */
	public void testPutCacheElement() {
		this.lruCache = new LRUCacheImpl();
		this.lruCache.put("1", "test");
		
		assertEquals(1, this.lruCache.getSize());
	}

	/**
	 * Test get an element from the LRU Cache.
	 */
	public void testGetCacheElement() {
		this.lruCache = new LRUCacheImpl();
		this.lruCache.put("1", "test");
		
		assertEquals("test", this.lruCache.get("1"));
	}
	
	/**
	 * Test remove least used cache element from the LRU Cache.
	 */
	public void testRemoveLeastUsedCacheElement() {
		this.lruCache = new LRUCacheImpl(3);
		this.lruCache.put("1", "test");
		this.lruCache.put("2", "test2");
		this.lruCache.put("3", "test3");
		this.lruCache.put("4", "test4");

		assertNull(this.lruCache.get("1"));
		assertNotNull(this.lruCache.get("2"));
		assertNotNull(this.lruCache.get("3"));
		assertNotNull(this.lruCache.get("4"));
	}
	
	/**
	 * Test remove least used cache element from the LRU Cache when getting the first
	 * element added, meaning it should stay in the cache.
	 */
	public void testRemoveLeastUsedCacheElemWhenAccessData() {
		this.lruCache = new LRUCacheImpl(3);
		this.lruCache.put("1", "test");
		this.lruCache.put("2", "test2");
		this.lruCache.put("3", "test3");
		this.lruCache.get("1");
		this.lruCache.put("4", "test4");

		assertNotNull(this.lruCache.get("1"));
		assertNull(this.lruCache.get("2"));
		assertNotNull(this.lruCache.get("3"));
		assertNotNull(this.lruCache.get("4"));
	}
}