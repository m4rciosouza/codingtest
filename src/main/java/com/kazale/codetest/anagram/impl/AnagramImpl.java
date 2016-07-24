package com.kazale.codetest.anagram.impl;

import java.util.ArrayList;
import java.util.List;

import com.kazale.codetest.anagram.Anagram;

/**
 * Class that implements the check for anagrams.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class AnagramImpl implements Anagram {

	private static final String REGEX_LETTERS = "[a-zA-Z]+";
	
	/**
	 * {@inheritDoc}
	 */
	public List<String> getValidAnagrams(final String word, final List<String> words) {
		List<String> anagrams = new ArrayList<String>();
		
		if(words == null) {
			return anagrams;
		}
		
		for(String anagram: words) {
			if(isAnagram(word, anagram)) {
				anagrams.add(anagram);
			}
		}
		
		return anagrams;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean isAnagram(String word, String anagram) {
		if(isInvalidWord(word) || isInvalidWord(anagram) 
				|| word.equalsIgnoreCase(anagram)) {
			return false;
		}
		
		int chars[] = new int[25]; // to hold the chars occurrences in both words
		boolean validAnagram = true;
		
		for(char character: anagram.toLowerCase().toCharArray()) {
			// parse the character to the index form 0-25
			int pos = Character.getNumericValue(character) - 10;
			chars[pos] ++;
		}
		
		for(char character: word.toLowerCase().toCharArray()) {
			// parse the character to the index form 0-25
			int pos = Character.getNumericValue(character) - 10;
			chars[pos] --;
		}
		
		// check if the array has all occurrences as 0, meaning the words are anagrams
		for(int i=0; i<chars.length; i++) {
			if(chars[i] != 0) {
				validAnagram = false;
			}
		}
		
		return validAnagram;
	}
	
	/**
	 * Return if a word is invalid. Check if it is not null, not empty, 
	 * and if it contains only characters in the range of [a-zA-Z].
	 * 
	 * @param word to be validate
	 * @return boolean if it is invalid.
	 */
	private boolean isInvalidWord(String word) {
		return (word == null || word.isEmpty() || !word.matches(REGEX_LETTERS));
	}
}
