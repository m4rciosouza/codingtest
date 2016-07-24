package com.kazale.codetest.datecalculation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.kazale.codetest.datecalculation.impl.LotteryDrawImpl;

import junit.framework.TestCase;

/**
 * Test class to the lottery draw calculation.
 * 
 * @author Marcio Souza<m4rcio.souza@gmail.com>
 * @version 0.0.1
 */
public class LotteryDrawTest extends TestCase {

	private LotteryDraw lotteryDraw;
	private SimpleDateFormat sdf;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.lotteryDraw = new LotteryDrawImpl();
		this.sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	}
	
	/**
	 * Test the draw dates for Wednesdays before the draw time (8pm).
	 */
	public void testCalculateWedBeforeDraw() {
		final Calendar date = Calendar.getInstance();
		date.set(2016, 6, 20, 19, 59, 59);
		
		final Map<Integer, Date> resultByDay = this.lotteryDraw.calculate(date.getTime());
		
		assertEquals("20-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.NEXT_DRAW)));
		assertEquals("23-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.SUPPLIED_DATE)));
	}
	
	/**
	 * Test the draw dates for Wednesdays after the draw time (8pm).
	 */
	public void testCalculateWedAfterDraw() {
		final Calendar date = Calendar.getInstance();
		date.set(2016, 6, 20, 21, 0, 0);
		
		final Map<Integer, Date> resultByDay = this.lotteryDraw.calculate(date.getTime());
		
		assertEquals("23-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.NEXT_DRAW)));
		assertEquals("27-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.SUPPLIED_DATE)));
	}
	
	/**
	 * Test the draw dates for Saturdays before the draw time (8pm).
	 */
	public void testCalculateSatBeforeDraw() {
		final Calendar date = Calendar.getInstance();
		date.set(2016, 6, 23, 18, 30, 0);
		
		final Map<Integer, Date> resultByDay = this.lotteryDraw.calculate(date.getTime());
		
		assertEquals("23-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.NEXT_DRAW)));
		assertEquals("27-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.SUPPLIED_DATE)));
	}

	/**
	 * Test the draw dates for Saturdays after the draw time (8pm).
	 */
	public void testCalculateSatAfterDraw() {
		final Calendar date = Calendar.getInstance();
		date.set(2016, 6, 23, 22, 30, 0);
		
		final Map<Integer, Date> resultByDay = this.lotteryDraw.calculate(date.getTime());
		
		assertEquals("27-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.NEXT_DRAW)));
		assertEquals("30-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.SUPPLIED_DATE)));
	}
	
	/**
	 * Test the draw dates when Tuesday.
	 */
	public void testCalculateWhenTuesday() {
		final Calendar date = Calendar.getInstance();
		date.set(2016, 6, 19, 22, 30, 0);
		
		final Map<Integer, Date> resultByDay = this.lotteryDraw.calculate(date.getTime());
		
		assertEquals("20-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.NEXT_DRAW)));
		assertEquals("23-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.SUPPLIED_DATE)));
	}
	
	/**
	 * Test the draw dates when Friday.
	 */
	public void testCalculateWhenFriday() {
		final Calendar date = Calendar.getInstance();
		date.set(2016, 6, 22, 14, 30, 0);
		
		final Map<Integer, Date> resultByDay = this.lotteryDraw.calculate(date.getTime());
		
		assertEquals("23-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.NEXT_DRAW)));
		assertEquals("27-07-2016 20:00:00", this.sdf.format(resultByDay.get(LotteryDraw.SUPPLIED_DATE)));
	}
}
