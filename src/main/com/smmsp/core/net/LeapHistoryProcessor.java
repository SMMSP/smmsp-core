/*
 * LeapHistoryProcessor.java
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
package com.smmsp.core.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.smmsp.time.GregorianDate;
import com.smmsp.time.GregorianDateRange;
import com.smmsp.time.TimeConstants;

/**
 * Downloads the leaps file from IANA tz database and provides
 * a method for determining how many leaps existed at a point in the
 * past.
 * 
 * @author sean
 *
 */
public class LeapHistoryProcessor{
	
	/**
	 * Log file!
	 */
	private static final Logger log = Logger.getLogger(LeapHistoryProcessor.class);
	
	/**
	 * The URL for which we will download the leaps file.
	 */
	private static final String LEAPS_URL = "ftp://ftp.iana.org/tz/data/leapseconds";
	
	/**
	 * Name of the file 
	 */
	private static final String LEAPS_CACHE_FILE = "leapseconds";
	
	/**
	 * The FTP cached file that we're going to use.
	 */
	private static final FTPCachedFile LEAPS_FILE = 
			new FTPCachedFile(LEAPS_CACHE_FILE, LEAPS_URL);
	
	/**
	 * Internal storage bit for the leaps history
	 */
	private LinkedHashMap<GregorianDateRange, Integer> _leapsRange;
	
	
	/**
	 * Actually go ahead and parse the file, pulling out the history
	 * and populating the internal data sites
	 */
	private void populateHistory(){
		if(LEAPS_FILE.cacheNeedsUpdate()){
			LEAPS_FILE.updateCache();
		}
		
		Path fullCachedFile = LEAPS_FILE.getPathToCache();
		
		try {
			FileChannel cachedFile = FileChannel.open(
					fullCachedFile,
					StandardOpenOption.READ
					);
			
			BufferedReader reader = new BufferedReader(
					Channels.newReader(cachedFile, "UTF-8"));
			
			String line = null;
			GregorianDate previousDate = null;
			int currentLeaps = 0;
			
			while((line = reader.readLine()) != null){
				line = line.trim();
				if("".equals(line)){
					continue; // blank lines ignored
				}else if(line.startsWith("#")){
					continue; // comments ignored
				}else if(!line.startsWith("Leap")){
					continue; // only looking for leaps
				}
				
				String[] fields = line.split("\t");
				if(!"Leap".equals(fields[0])){
					// fields[0] should be leap, if not, processing error
					continue;
				}
				int year = Integer.parseInt(fields[1]);
				String month = fields[2];
				int day = Integer.parseInt(fields[3]);
				
				if("+".equals(fields[5])){
					++currentLeaps;
				}else{
					--currentLeaps;
				}
				
				GregorianDate date = new GregorianDate(
						year, 
						TimeConstants.monthStringToInt(month), 
						day);
				GregorianDateRange range = 
						new GregorianDateRange(previousDate, date);
				_leapsRange.put(range, currentLeaps);
				
				previousDate = date;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Unable to read leaps cache file. ", e);
			return;
		}
		
		
	}
	
	public int getLeapsForDate(GregorianDate date){
		if(_leapsRange == null ){
			_leapsRange = new LinkedHashMap<>();
			populateHistory();
		}
		
		Map.Entry<GregorianDateRange, Integer> finalRange = null;
		for(Map.Entry<GregorianDateRange, Integer> ents : _leapsRange.entrySet()){
			GregorianDateRange range = ents.getKey();
			System.out.println(ents);
//			if(range.isBefore(date)){
//				TODO finish this.
//			}
			finalRange = ents;
		}
		
		if(finalRange.getKey().isWithinRange(date)){
			return finalRange.getValue();
		}
		
		return 0;
	}


}
