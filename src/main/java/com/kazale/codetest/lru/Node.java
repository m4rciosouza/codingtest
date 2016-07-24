package com.kazale.codetest.lru;

/**
 * Class that represents a single node of a double linked list used in the 
 * LRU (Least Recently Used) cache.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class Node {

	private String key;
	private String value;
	private Node prev;
	private Node next;
	
	/**
	 * Default constructor.
	 */
	public Node() {
	}
	
	/**
	 * Constructor with args.
	 * 
	 * @param key
	 * @param value
	 */
	public Node(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Return the key.
	 * 
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Set the key.
	 *  
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Return the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set the value.
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Return the previous Node.
	 * 
	 * @return previous Node
	 */
	public Node getPrev() {
		return prev;
	}

	/**
	 * Set the privious Node.
	 * 
	 * @param previous Node
	 */
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	/**
	 * Return the next Node.
	 * 
	 * @return next Node
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Set the next Node.
	 * 
	 * @param next Node
	 */
	public void setNext(Node next) {
		this.next = next;
	}
}
