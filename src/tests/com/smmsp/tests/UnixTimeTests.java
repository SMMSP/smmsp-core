/**
 * 
 */
package com.smmsp.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smmsp.time.TimeConstants;
import com.smmsp.time.TimeException;
import com.smmsp.time.UnixTime;

/**
 * @author sean
 *
 */
public class UnixTimeTests {

	/**
	 * Test method for {@link com.smmsp.time.UnixTime#toTimestamp()}.
	 * @throws TimeException 
	 */
	@Test
	public void testToTimestampEpoch() throws TimeException {
		UnixTime time = new UnixTime(1970, 1, 1);
		
		assertEquals(0, time.toTimestamp());
	}
	
	@Test
	public void testToTimestampFirstDay() throws TimeException{
		UnixTime time = new UnixTime(1970, 1, 2);
		assertEquals(TimeConstants.SECONDS_IN_DAY, time.toTimestamp());

		time = new UnixTime(1970, 1, 2, 0, 0, 0);
		assertEquals(TimeConstants.SECONDS_IN_DAY, time.toTimestamp());
		
		time = new UnixTime(1970, 1, 1, 24, 0, 0);
		assertEquals(TimeConstants.SECONDS_IN_DAY, time.toTimestamp());
		
		time = new UnixTime(1970, 1, 1, 23, 60, 0);
		assertEquals(TimeConstants.SECONDS_IN_DAY, time.toTimestamp());
		
		time = new UnixTime(1970, 1, 1, 23, 59, 60);
		assertEquals(TimeConstants.SECONDS_IN_DAY, time.toTimestamp());
		
		time = new UnixTime(1970, 1, 1, 23, 59, 59);
		assertEquals(TimeConstants.SECONDS_IN_DAY - 1, time.toTimestamp());
	}
	
	@Test
	public void testTimestampFirstDayFirstSecond() throws TimeException{
		UnixTime time = new UnixTime(1970, 1, 2, 0, 0, 1);
		
		assertEquals(TimeConstants.SECONDS_IN_DAY + 1, time.toTimestamp());
	}

	@Test
	public void testTimestampSecondDay() throws TimeException{
		UnixTime time = new UnixTime(1970, 1, 3, 0, 0, 0);
		
		assertEquals(TimeConstants.SECONDS_IN_DAY * 2, time.toTimestamp());
	}
	
	@Test
	public void testWikipediaTests() throws TimeException{
		
	}
}
