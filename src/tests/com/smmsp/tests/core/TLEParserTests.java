/*
 * TLEParserTests.java
 * 
 * Copyright (C) 2013 Sean P Madden
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 * If you would like to license this code under the GNU LGPL, please
 * see http://www.seanmadden.net/licensing for details.
 */
package com.smmsp.tests.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.smmsp.core.tle.TLE;
import com.smmsp.core.tle.TLEProcessor;

/**
 * @author sean
 *
 */
public class TLEParserTests {

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChecksum() {
		String line = "1 23455U 94089A   97320.90946019  .00000140  00000-0  10191-3 0  2621";
		
		assertTrue(TLEProcessor.checksumMatches(line));

		line = "2 23455  99.0090 272.6745 0008546 223.1686 136.8816 14.11711747148495";
		assertTrue(TLEProcessor.checksumMatches(line));
	}
	
	@Test
	public void testParserTwoLine(){
		final String[] lines = {
				"1 23455U 94089A   97320.90946019  .00000140  00000-0  10191-3 0  2621",
				"2 23455  99.0090 272.6745 0008546 223.1686 136.8816 14.11711747148495"
		};
		
		TLE theTle = TLEProcessor.fromString(lines);
		
		System.out.println(theTle);
	}
	
	@Test
	public void testParserThreeLine(){
		final String[] lines = { 
				"NOAA 14                 ",
				"1 23455U 94089A   97320.90946019  .00000140  00000-0  10191-3 0  2621",
				"2 23455  99.0090 272.6745 0008546 223.1686 136.8816 14.11711747148495"
		};
		
		TLE theTle = TLEProcessor.fromString(lines);
		
		System.out.println(theTle);
	}

}
