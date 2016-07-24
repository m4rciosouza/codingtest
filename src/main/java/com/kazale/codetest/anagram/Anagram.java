package com.kazale.codetest.anagram;

import java.util.List;

/**
 * Interface to check the anagrams.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public interface Anagram {

	/**
	 * Method the get a word and a list of words and check the anagrams for the given word, 
	 * returning a list of anagrams found.
	 * 
	 * This method assumes the chars used are in the range [a-zA-Z]
	 * 
	 * @param word to be checked for anagrams
	 * @param words 
	 * @return list of anagrams found or empty list when nothing found.
	 */
	List<String> getValidAnagrams(String word, List<String> words); 
	
	/**
	 * Method to check and returns if two words are anagrams.
	 * It holds a list with the number of occurrences of each char for the anagram word, 
	 * then try to match to each char found to the given word, as a result if the char list 
	 * has all empty positions means the two words are anagrams.
	 * 
	 * It considers only chars in the range of [a-zA-Z].
	 * 
	 * @param word
	 * @param anagram
	 * @return boolean if is or not an anagram
	 */
	boolean isAnagram(String word, String anagram);
}
