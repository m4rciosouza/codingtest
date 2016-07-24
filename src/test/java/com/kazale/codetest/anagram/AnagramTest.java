package com.kazale.codetest.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kazale.codetest.anagram.impl.AnagramImpl;

import junit.framework.TestCase;

/**
 * Test class for the anagram class.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class AnagramTest extends TestCase {

	private Anagram anagram;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.anagram = new AnagramImpl();
	}

	/**
	 * Test if it is anagram for null words.
	 */
	public void testIsAnagramWhenNullWords() {
		assertFalse(this.anagram.isAnagram(null, null));
	}
	
	/**
	 * Test if it is anagram for empty words.
	 */
	public void testIsAnagramWhenEmptyWords() {
		assertFalse(this.anagram.isAnagram("", ""));
	}
	
	/**
	 * Test if it is anagram for valid words.
	 */
	public void testIsAnagramWhenValidWords() {
		assertTrue(this.anagram.isAnagram("test", "estt"));
		assertTrue(this.anagram.isAnagram("Test", "seTt"));
	}
	
	/**
	 * Test if it is anagram for invalid words.
	 */
	public void testIsAnagramWhenInvalidWords() {
		assertFalse(this.anagram.isAnagram("test", "anagram"));
		assertFalse(this.anagram.isAnagram("test", "testt"));
		assertFalse(this.anagram.isAnagram("teça", "tédá"));
	}
	
	/**
	 * Test when the given word is null.
	 */
	public void testGetValidAnagramsWhenNullWord() {
		List<String> anagrams = this.anagram.getValidAnagrams(null, new ArrayList<String>());
		
		assertTrue(anagrams.isEmpty());
	}
	
	/**
	 * Test when the given list of words is null.
	 */
	public void testGetValidAnagramsWhenNullWordsList() {
		List<String> anagrams = this.anagram.getValidAnagrams("Test", null);
		
		assertTrue(anagrams.isEmpty());
	}

	/**
	 * Test when no anagrams are found for the given word and list of words.
	 */
	public void testGetValidAnagramsWhenNOAnagramFound() {
		List<String> words = Arrays.asList("atest", "valid", "anagram");

		List<String> anagrams = this.anagram.getValidAnagrams("Test", words);
		
		assertTrue(anagrams.isEmpty());
	}
	
	/**
	 * Test when only one anagram is found for the given word and list of words.
	 */
	public void testGetValidAnagramsWhenOnlyOneFound() {
		List<String> words = Arrays.asList("test", "etts", "anagram");

		List<String> anagrams = this.anagram.getValidAnagrams("Test", words);
		
		assertEquals(1, anagrams.size());
		assertEquals("etts", anagrams.get(0));
	}
	
	/**
	 * Test when three anagrams are found for the given word and list of words.
	 */
	public void testGetValidAnagramsWhenThreeFound() {
		List<String> words = Arrays.asList("test", "etts", "éSanaágramç", "etst", "Stte");

		List<String> anagrams = this.anagram.getValidAnagrams("Test", words);
		
		assertEquals(3, anagrams.size());
		assertEquals("etts", anagrams.get(0));
		assertEquals("etst", anagrams.get(1));
		assertEquals("Stte", anagrams.get(2));
	}
}
