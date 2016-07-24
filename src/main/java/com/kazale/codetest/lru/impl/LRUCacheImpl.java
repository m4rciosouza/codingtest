package com.kazale.codetest.lru.impl;

import java.util.HashMap;
import java.util.Map;

import com.kazale.codetest.lru.LRUCache;
import com.kazale.codetest.lru.Node;

/**
 * Class that implements the LRU (Least Recently Used) cache.
 * 
 * Implement the cache as a double linked list in order to move 
 * all the nodes easily when promoting to the head or least used.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 * @param <T>
 */
public class LRUCacheImpl implements LRUCache {
	
	private static final int DEFAULT_MAX_SIZE = 100;
	private final int maxSize;
	private final Map<String, Node> cache;
	private Node head = null;
    private Node last = null;
	
	/**
	 * Default constructor.
	 */
	public LRUCacheImpl() {
		this.maxSize = DEFAULT_MAX_SIZE;
		this.cache = new HashMap<String, Node>(this.maxSize);
	}
	
	/**
	 * Constructor with the max size.
	 * 
	 * @param maxSize
	 */
	public LRUCacheImpl(int maxSize) {
		this.maxSize = maxSize;
		this.cache = new HashMap<String, Node>(this.maxSize);
	}

	/**
	 * {@inheritDoc}
	 */
	public String get(String key) {
		String value = null;
		
		if(this.cache.containsKey(key)) {
            Node node = this.cache.get(key);
            this.remove(node);
            this.setHead(node); // define the current node as the least used / head
            value = node.getValue();
        }
 
        return value;
	}

	/**
	 * {@inheritDoc}
	 */
	public void put(String key, String value) {
		if(this.cache.containsKey(key)) {
			// set as head in the cache
            Node oldNode = this.cache.get(key);
            oldNode.setValue(value);
            remove(oldNode);
            setHead(oldNode);
        } else {
        	// create a new node and set it as the head
            Node node = new Node(key, value);
            if(this.cache.size() >= this.maxSize) {
                this.cache.remove(this.last.getKey());
                this.remove(this.last);
                this.setHead(node);
            } else {
                this.setHead(node);
            }    
 
            this.cache.put(key, node);
        }
	}
	
	/**
	 * Set the new head to the double linked list.
	 * 
	 * @param node
	 */
	private void setHead(Node node){
		node.setPrev(null);
        node.setNext(this.head);
 
        if(this.head != null) {
        	this.head.setPrev(node);
        }
 
        this.head = node;
 
        if(this.last == null) {
        	this.last = this.head;
        }
    }
	
	/**
	 * Remove an element from the cache.
	 * 
	 * @param node
	 */
	private void remove(Node node){
        if(node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            this.head = node.getNext();
        }
 
        if(node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            this.last = node.getPrev();
        }
    }

	/**
	 * {@inheritDoc}
	 */
	public int getMaxSize() {
		return maxSize;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getSize() {
		return cache.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void printData() {
		System.out.println("### Displaying cache data:");
		for(Map.Entry<String, Node> entry : this.cache.entrySet()) {
			Node node  = entry.getValue();
		    System.out.println(node.getKey() + ": " + node.getValue());
		}
		System.out.println("### End of cache data.");
	}
}