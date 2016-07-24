package com.kazale.codetest.datecalculation.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.kazale.codetest.datecalculation.LotteryDraw;

/**
 * Class that implements the lottery draw calculation.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class LotteryDrawImpl implements LotteryDraw {

	private static final int LOTTERY_DRAW_HOUR = 20; // 8pm
	private static final int DAYS_BETWEEN_WED_SAT = 3;
	private static final int DAYS_BETWEEN_SAT_WED = 4;
	
	/**
	 * {@inheritDoc}
	 */
	public Map<Integer, Date> calculate() {
		return calculate(new Date());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<Integer, Date> calculate(final Date date) {
		 final Map<Integer, Date> result = new HashMap<Integer, Date>(2);
		 
		 final Calendar dateCal = Calendar.getInstance();
		 dateCal.setTime(date);

		 // find the first Wednesday or Saturday from the current date
		 while(dateCal.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY 
				 && dateCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			 dateCal.add(Calendar.DATE, 1);
			 dateCal.set(Calendar.HOUR_OF_DAY, 0);
		 }
		 
		 // set the previous and supplied dates
		 if(dateCal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
			 setDatesForWed(result, dateCal);
		 } else {
			 setDatesForSat(result, dateCal);
		 }
		 
		return result;
	}

	/**
	 * Set the next and supplied dates when date is Saturday.
	 * If the time is after the draw time (8pm), the next will Wed and supplied Sat.
	 * 
	 * @param result
	 * @param dateCal
	 */
	private void setDatesForSat(Map<Integer, Date> result, Calendar dateCal) {
		if(beforeEight(dateCal)) {
			setDates(result, dateCal, DAYS_BETWEEN_SAT_WED);
		} else {
			dateCal.add(Calendar.DATE, DAYS_BETWEEN_SAT_WED); // move to the next Wednesday
			setDates(result, dateCal, DAYS_BETWEEN_WED_SAT);
		}
		
	}

	/**
	 * Set the next and supplied dates when date is Wednesday.
	 * If the time is after the draw time (8pm), the next will Sat and supplied Wed.
	 * 
	 * @param result
	 * @param dateCal
	 */
	private void setDatesForWed(Map<Integer, Date> result, Calendar dateCal) {
		if(beforeEight(dateCal)) {
			setDates(result, dateCal, DAYS_BETWEEN_WED_SAT);
		} else {
			dateCal.add(Calendar.DATE, DAYS_BETWEEN_WED_SAT); // move to the next Saturday
			setDates(result, dateCal, DAYS_BETWEEN_SAT_WED);
		}
	}

	/** 
	 * Return if a date time is before 8pm.
	 * 
	 * @param date
	 * @return boolean
	 */
	private boolean beforeEight(Calendar date) {
		if(date == null) {
			return false;
		}
		
		return date.get(Calendar.HOUR_OF_DAY) < LOTTERY_DRAW_HOUR;
	}

	/**
	 * Set the next and supplied draw dates, as well as set the draw time to 8pm.
	 * 
	 * @param result
	 * @param date
	 * @param intervalBetweenDates
	 */
	private void setDates(Map<Integer, Date> result, Calendar date, int intervalBetweenDates) {
		date.set(Calendar.HOUR_OF_DAY, LOTTERY_DRAW_HOUR);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		
		result.put(NEXT_DRAW, date.getTime());
		date.add(Calendar.DATE, intervalBetweenDates);
		result.put(SUPPLIED_DATE, date.getTime());
	}
}
