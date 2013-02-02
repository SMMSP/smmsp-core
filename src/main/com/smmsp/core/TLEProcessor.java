/*
 * TLEProcessor.java
 * 
 * Copyright (C) 2012 Sean P Madden
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
package com.smmsp.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import com.smmsp.time.GregorianDate;

/**
 * @author sean
 * 
 */
public final class TLEProcessor {

	private TLEProcessor() {
		// do nothing.
	}

	public static TLE[] fromInputStream(final InputStream is) throws TLEException{
		final BufferedReader read = new BufferedReader(new InputStreamReader(is));
		
		final LinkedList<String> strings = new LinkedList<String>();
		final ArrayList<TLE> ret = new ArrayList<TLE>();
		
		String line = "";
		try {
			while((line = read.readLine()) != null){
				strings.add(line);
			}
		} catch (IOException e) {
			throw new TLEException("Unable to get line", e);
		}
		
		if(strings.size() % 3 != 0){
			throw new TLEException("Wrong number of lines!");
		}
		
		while(!strings.isEmpty()){
			final ArrayList<String> list = new ArrayList<String>();
			list.add(strings.pollFirst());
			list.add(strings.pollFirst());
			list.add(strings.pollFirst());
			String[] arr = list.toArray(new String[list.size()]);
			ret.add(fromString(arr));
			
		}
		
		return ret.toArray(new TLE[ret.size()]);
	}
	
	public static TLE fromString(String[] lines) throws TLEException {
		final int numLines = lines.length;
		if (numLines > 3) {
			throw new TLEException("Too many lines found: " + numLines);
		} else if (numLines < 2) {
			throw new TLEException("Not enough lines found: " + numLines);
		}

		final TLE theTLE = new TLE();

		if (numLines == 3) {
			theTLE.satelliteName = lines[0].trim();
			processFirstLine(theTLE, lines[1]);
			processSecondLine(theTLE, lines[2]);
		} else if (numLines == 2) {
			processFirstLine(theTLE, lines[0]);
			processSecondLine(theTLE, lines[1]);
		}

		return theTLE;
	}

	protected static void processFirstLine(final TLE out, final String line)
			throws TLEException {
		if(!checksumMatches(line)){
			throw new TLEException("Checksum doesn't match on line 1");
		}
		try {
			out.satelliteNumber = Integer.valueOf(line.substring(2, 7).trim());
			final char classification = line.charAt(7);
			out.internationalDesignator = line.substring(9, 17).trim();
			int epochYear = Integer.valueOf(line.substring(18, 20).trim());
			if (epochYear < 57) {
				// assume we didn't launch anything prior to 1957
				epochYear += 2000;
			} else {
				epochYear += 1900;
			}
			final double epochDay = Double.valueOf(line.substring(20, 32).trim());
			out.dotMeanMotion = Double.valueOf(line.substring(33, 43).trim());
			String dotdot = line.substring(44, 52).trim();
			dotdot = dotdot.replace("-", "E-");
			out.dotDotMeanMotion = Double.valueOf(dotdot);
			String bstar = line.substring(53, 61).trim();
			bstar = bstar.replace("-", "E-");
			bstar = bstar.replace("+", "E+");
			out.bstarDragTerm = Double.valueOf(bstar);
			out.elementSetNumber = Integer.valueOf(line.substring(63, 68)
					.trim());

			out.epoch = GregorianDate.fromYearAndDay(epochYear, (int) epochDay);
			out.classification = Classification.fromChar(classification);
		} catch (Exception e) {
			throw new TLEException("Error processing TLE", e);
		}
	}

	protected static void processSecondLine(final TLE out, final String line)
			throws TLEException {
		if(!checksumMatches(line)){
			throw new TLEException("Checksum doesn't match on line 2");
		}
		try {
			out.inclinationDeg = Double.valueOf(line.substring(8, 16).trim());
			out.raanDeg = Double.valueOf(line.substring(17, 25).trim());
			
			final String ecc = "." + line.substring(26, 33).trim();
			out.eccentricity = Double.valueOf(ecc);
			
			out.argPerigeeDeg = Double.valueOf(line.substring(34, 42).trim());
			out.meanAnomalyDeg = Double.valueOf(line.substring(43, 51).trim());
			out.meanMotionRPD = Double.valueOf(line.substring(52,  63).trim());
			out.revNumberAtEpoch = Integer.valueOf(line.substring(63, 68).trim());
			
			
		} catch (Exception e) {
			throw new TLEException("Error processing TLE", e);
		}
	}

	public static boolean checksumMatches(final String line) {
		final int checksum = line.charAt(68) - '0';

		int rolling = 0;
		char[] arr = line.toCharArray();
		char c = arr[0];
		for (int i = 0; i < 68; ++i, c = arr[i]) {
			if (Character.isDigit(c)) {
				rolling += (c - '0');
			} else if (c == '-') {
				++rolling;
			}
		}

		return checksum == (rolling % 10);
	}
}
