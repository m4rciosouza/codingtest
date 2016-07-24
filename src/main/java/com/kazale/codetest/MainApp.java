package com.kazale.codetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kazale.codetest.anagram.Anagram;
import com.kazale.codetest.anagram.impl.AnagramImpl;
import com.kazale.codetest.datecalculation.LotteryDraw;
import com.kazale.codetest.datecalculation.impl.LotteryDrawImpl;
import com.kazale.codetest.lru.LRUCache;
import com.kazale.codetest.lru.impl.LRUCacheImpl;

/**
 * Main class to run a console based application.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class MainApp {
	
	private String optionNumber;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	/**
	 * Get the input data from console screen to be processed.
	 * 
	 * @param in scanner to get the user input
	 */
	private void getInputData(Scanner in) {
		
		do {
			System.out.println("\nEnter the option's number to execute: ");
			optionNumber = in.nextLine();
		} while(!validateOptionNumber(optionNumber));
		
		switch(Integer.parseInt(optionNumber)) {
			case 1:
				runLotteryDraw(in);
				break;
			case 2:
				runLru();
				break;
			case 3:
				runAnagram(in);
				break;
			default:
				return;
		}
		
		System.out.println("\nPress enter to continue...");
		in.nextLine();
		run();
	}
	
	/**
	 * Run the lottery draw console example.
	 * 
	 * @param in scanner to get the user input
	 */
	private void runLotteryDraw(Scanner in) {
		System.out.println("\nEnter the date to test (dd-mm-yyyy HH:mm:ss) or leave blank for current date: ");
		String dateText = in.nextLine();
		
		try {
			Date date;
			if(dateText == null || dateText.trim().isEmpty()) {
				date = new Date();
			} else {
				date = this.sdf.parse(dateText);				
			}
			LotteryDraw lotteryDraw = new LotteryDrawImpl();
			Map<Integer, Date> dates = lotteryDraw.calculate(date);
			System.out.println("The next draw date will be: " + this.sdf.format(dates.get(LotteryDraw.NEXT_DRAW)));
			System.out.println("The supplied draw date will be: " + this.sdf.format(dates.get(LotteryDraw.SUPPLIED_DATE)));
		} catch (ParseException e) {
			System.out.println("Invalid date...");
		}
	}

	/**
	 * Run the anagram console example.
	 * 
	 * @param in scanner to get the user input
	 */
	private void runAnagram(Scanner in) {
		System.out.println("\nEnter first the word to check for an anagram: ");
		String word = in.nextLine();
		System.out.println("\nEnter one or more word (separated by comma) to check for an anagram: ");
		String words = in.nextLine();
		List<String> wordsList =  Arrays.asList(words.split(","));
		
		Anagram anagram = new AnagramImpl();
		List<String> validAnagrams = anagram.getValidAnagrams(word, wordsList);
		System.out.println("The valid anagrams for the word and word list given are:");
		System.out.println(Arrays.toString(validAnagrams.toArray()));
	}

	/**
	 * Display a sample run of the LRU Cache when:
	 * 
	 * 1. Create a LRU Cache with max size of 3
	 * 2. Add three entries (T1, T2 and T3)
	 * 3. Get the T1 from cache since it is the least used in order to promote T2 to be the least used
	 * 4. Add a new entry (T4), this way the least used (T2) will be removed from the cache.
	 */
	private void runLru() {
		LRUCache lruCache = new LRUCacheImpl(3);
		System.out.println("Running a sample LRU Cache test.");
		System.out.println("It will create a small cache (max size 3), and add 3 entries (T1, T2, T3).");
		System.out.println("Then before add the fourth entry, it will get the first one (that is supposed to be the least used.");
		System.out.println("The least used will be the T2, so the fourt (T4) will be added and the T2 will be removed from the cache.");
		System.out.println("\nAdding T1 and displaying the cache data:\n");
		lruCache.put("1", "T1");
		lruCache.printData();
		System.out.println("\nAdding T2 and displaying the cache data:\n");
		lruCache.put("2", "T2");
		lruCache.printData();
		System.out.println("\nAdding T3 and displaying the cache data:\n");
		lruCache.put("3", "T3");
		lruCache.printData();
		System.out.println("\nGetting T1 from cache and displaying the cache data:\n");
		lruCache.get("1");
		lruCache.printData();
		System.out.println("\nAdding T4 and displaying the cache data:\n");
		lruCache.put("4", "T4");
		lruCache.printData();
	}

	/**
	 * Validate the input console value for the option number.
	 * 
	 * @param optionNumber
	 * @return boolean
	 */
	private boolean validateOptionNumber(String optionNumber) {
		boolean validOption = false;

		try {
			int option = Integer.parseInt(optionNumber);
			if(option >= 1 && option <= 4) {
				validOption = true;
			}
		} catch(NumberFormatException nfe) {
			return false;
		}
		
		return validOption;
	}
	
	/**
	 * Run the console application.
	 */
	private void run() {
		System.out.println("### Select an option to run:\n");
		System.out.println("1. Date Calculation");
		System.out.println("2. LRU Cache");
		System.out.println("3. Anagram");
		System.out.println("4. Exit");
		
		Scanner in = new Scanner(System.in);
		getInputData(in);
	}
	
	/**
	 * Main app method.
	 * 
	 * @param args
	 */
    public static void main( String[] args )
    {
    	MainApp app = new MainApp();
    	app.run();
    }
}
