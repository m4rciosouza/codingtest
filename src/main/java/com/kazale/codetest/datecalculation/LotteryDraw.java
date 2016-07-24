package com.kazale.codetest.datecalculation;

import java.util.Date;
import java.util.Map;

/**
 * Interface to define the methods used for the lottery draw calculation.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public interface LotteryDraw {

	public static final Integer NEXT_DRAW = 1;
	public static final Integer SUPPLIED_DATE = 2;
	
	/**
	 * Calculate and return a map with the next draw date, as well as a supplied date 
	 * for the current date.
	 * 
	 * @param Date date to be used to calculate the next draw.
	 * @return Map<Integer, Date> with the next draw date and the supplied one.
	 */
	Map<Integer, Date> calculate();
	
	/**
	 * Calculate and return a map with the next draw date, as well as a supplied date.
	 * 
	 * @param Date date to be used to calculate the next draw.
	 * @return Map<Integer, Date> with the next draw date and the supplied one.
	 */
	Map<Integer, Date> calculate(Date date);
}
